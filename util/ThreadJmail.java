package util;

import util.JMailUtil;

/**
 * Created with IntelliJ IDEA.
 * User: 戴虎
 * Date: 2018/08/25
 * Description:
 * Version: V1.0
 */
public class ThreadJmail extends Thread {
    private String content;
    private String to;
    private String subject;
    public ThreadJmail(String content,String to,String subject){
        this.content = content;
        this.to = to;
        this.subject = subject;
    }
    public void run(){
        new JMailUtil().sendEmail(content,subject,to);
    }
}
