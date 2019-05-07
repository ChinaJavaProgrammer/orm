package util;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.*;

/**
 * 发送邮件
 */
public class JMailUtil {

    private String sendTo;
    private String sendContent;
    private String sendSubject;
    private String sender="daihu_code@sina.com";
    private String senderPassword="atvvmnggaxejbcei";
    private Map<String,String>emialInfo = new HashMap<>();
    private String smtp;
    public JMailUtil(){
        emialInfo.put("google.com","smtp.gmail.com:587");
        emialInfo.put("21cn.com","smtp.21cn.com:25");
        emialInfo.put("sina.com","smtp.sina.com.cn:25");
        emialInfo.put("tom.com","smtp.tom.com:25");
        emialInfo.put("163.com","smtp.163.com:25");
        emialInfo.put("263.net","smtp.263.net:25");
        emialInfo.put("yahoo.com","smtp.mail.yahoo.com:465");
        emialInfo.put("263.net.cn","smtp.263.net.cn:25");
        emialInfo.put("foxmail","SMTP.foxmail.com:25");
        emialInfo.put("sinaVIP","smtp.vip.sina.com:25");
        emialInfo.put("sohu.com","smtp.sohu.com:25");
        emialInfo.put("etang.com","smtp.etang.com");
        emialInfo.put("yahoo.com.cn","smtp.mail.yahoo.com.cn:587");
        emialInfo.put("qq.com","smtp.qq.com:587");
        emialInfo.put("china.com","smtp.china.com:25");
        emialInfo.put("hotmail","smtp.live.com:587");

    }
    /**
     * 设置发送者的邮箱如果不设置默认是oneday.life@qq.com
     * @param sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 设置发送者的邮箱密码或者授权码如果默认为atvvmnggaxejbcei
     * @param senderPassword
     */
    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }

    /**
     * 设置收件人地址
     * @param sendTo
     */
    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    /**
     * 设置需要发送的内容
     * @param sendContent
     */
    public void setSendContent(String sendContent) {
        this.sendContent = sendContent;
    }

    /**
     * 设置邮件主题
     * @param sendSubject
     */
    public void setSendSubject(String sendSubject) {
        this.sendSubject = sendSubject;
    }

    /**
     * 发送邮件
     * @throws Exception
     */
    public void sendEmail(String content,String subject,String to){
        setSendContent(content);
        setSendSubject(subject);
        setSendTo(to);
        Properties props = new Properties();
        props.setProperty("mail.debug","true");
        props.setProperty("mail.smtp.auth","true");
        //使用的服务器协议
        props.setProperty("mail.transport.protocol","smtp");
        Session session = Session.getInstance(props);
        session.setDebug(true);
        Message message = new MimeMessage(session);
        BodyPart bodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart("related");
        try {
            bodyPart.setContent(sendContent, "text/html;charset=utf-8");
            multipart.addBodyPart(bodyPart);
            message.setFrom(new InternetAddress(sender));
            message.setSubject(sendSubject);
            message.setContent(multipart);
            Transport transport = session.getTransport();
            //emialInfo.get(sender.substring(sender.indexOf("@")+1)).split(":")[0]
            transport.connect(emialInfo.get(sender.substring(sender.indexOf("@") + 1)).split(":")[0], Integer.parseInt(emialInfo.get(sender.substring(sender.indexOf("@") + 1)).split(":")[1]), sender,"823898876");
            transport.sendMessage(message, new Address[]{new InternetAddress(sendTo)});
            transport.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
