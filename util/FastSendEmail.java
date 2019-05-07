package util;


/**
 * Created with IntelliJ IDEA.
 * User: 戴虎
 * Date: 2018/08/26
 * Description:
 * Version: V1.0
 */
public class FastSendEmail {


    /**
     * 发邮件
     * @param name                  客户名称
     * @param dingdanhao            订单号
     * @param dangqianshenhejindu  当前订单审核进度（比如通过了xx审核）
     * @param email                 客户的邮箱
     */
    public static void fastSendEmail(String name,String dingdanhao,String dangqianshenhejindu,String email){
//        JMailHtml jMailHtml = new JMailHtml();
//        jMailHtml.setShenhe(name,dingdanhao,dangqianshenhejindu);
//        ThreadJmail threadJmail = new ThreadJmail(jMailHtml.getShenhe(),email,"您的订单审核进度更新啦");
//        threadJmail.start();
    }
}
