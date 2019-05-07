package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;


public class UploadPicture {
	
	private static final String UPLOAD_PATH="img";
	private static final int MAX_FILE_SIZE=1024*1024*50;
	private static final int MAX_REQUEST_SIZE=1024*1024*60;
	private static final int MEMORY_THRESHOLD=1024*1024*3;
	public static JSONObject uploadPicture(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String file_path ="";
		  JSONObject json = new JSONObject();
		  boolean  state=false;
		  String name = null;
		//检查是否为多媒体上传
		if(!ServletFileUpload.isMultipartContent(request))
		{
			/*PrintWriter out = response.getWriter();
		     out.print("ERROR：表单必须包括 enctype=multipart/form-data");
		     out.flush();
		     out.close();*/
		     json.put("fileName", name);
		     return json;
		}
		//是创建FileItem对象的工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//设置内存缓冲区大小
		//Sets the size threshold beyond which files are written directly to disk.
		System.out.println("DiskFileItemFactory默认缓冲区大小："+factory.getSizeThreshold());
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		System.out.println("DiskFileItemeFactory的默认临时存储文件夹："+factory.getRepository());
	
		//设置临时存储目录
		//Sets the directory used to temporarily store files that are larger than the configured size threshold.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		System.out.println("临时文件夹"+System.getProperty("java.io.tmpdir"));
		//高水平的api文件上传处理
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		
		//设置最大文件上传值
		fileUpload.setFileSizeMax(MAX_FILE_SIZE);
		//设置最大请求值包含文件和表单数据
		fileUpload.setSizeMax(MAX_REQUEST_SIZE);
		//中文处理
		fileUpload.setHeaderEncoding("utf-8");
		//构造临时路径来上传文件 这个路径相当于当前应用的目录
		String uploadPath = request.getServletContext().getRealPath(".")+File.separator +UPLOAD_PATH;
		File file = new File(uploadPath);
		if(!file.exists()){
			file.mkdir();
		}
		//解析请求的内容提取文件数据
		try {
			//formIteme This class represents a file or form item that was received within a multipart/form-data POST request.
			//parseRequest(request);处理RFC 1867兼容的多部分/表单数据流。processes an rfc 1867 compliant multipart/form-data stream.
			List<FileItem>formItems = fileUpload.parseRequest(request);
			if(formItems!=null&&formItems.size()>0)
			{
				Map<String,String> tempMap = new HashMap<String,String>();
				//迭代表单数据
				for (FileItem fileItem : formItems) {
					//判断如果是简单的form表单的简单字段则返回true。如果有上传文件则返回false
					//Determines whether or not a FileItem instance represents a simple form field.
					//true if the instance represents a simple form field; false if it represents an uploaded file.
					if(!fileItem.isFormField())
					{
						String fileName = new File(fileItem.getName()).getName();
						fileName=fileName.substring(fileName.lastIndexOf("."));
						String filePath = uploadPath + File.separator+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) +fileName;
						File storeFile = new File(filePath);
						//在控制台输出文件的上传路径
						System.out.println(filePath);
						//保存到硬盘
						fileItem.write(storeFile);
						name = filePath;
						file_path = filePath;
						tempMap.put(fileItem.getFieldName(), filePath);
					}else{
						tempMap.put(fileItem.getFieldName(), fileItem.getString());
					}
				}
				
				json.put("params", tempMap);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}


		return json;
	}
}
