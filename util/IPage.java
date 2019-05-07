package util;

import java.util.ArrayList;
import java.util.List;

/** 
 * @description 分页查询工具类
 * @author daihu
 * @param <T>
 * 2019年4月4日
 * version：basic
 *
 */
public class IPage<T> {
	
	private  Integer totalCount;
	
	private Integer totalPage;
	
	private Integer currentPage;
	
	private Integer dataNum;
	
	private List<T> data = new ArrayList<T>();

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getDataNum() {
		return dataNum;
	}

	public void setDataNum(Integer dataNum) {
		this.dataNum = dataNum;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
