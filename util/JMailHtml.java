package util;

/**
 * Created with IntelliJ IDEA.
 * User: 戴虎
 * Date: 2018/08/17
 * Description: JMail发送邮件正文部分
 * Version: V1.0
 */
public class JMailHtml {

    private String htmlContent;

    private String findPassword;

    private String happyBorthday;

    private String validateCode;

    private String xiadan;

    private String shenhe;

    public void setHtmlContent(String name,String href){
        htmlContent=" <style>\n" +
                "\t .maindiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 1159px;\n" +
                "\t\t border-left: 1px solid #e3e3e3;\n" +
                "\t\t border-right: 1px solid #e3e3e3;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                "\t .adiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 40px;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t\t margin-bottom: 10px;\n" +
                "\t\t margin-top: 20px;\n" +
                "\t\t line-height: 40px;\n" +
                "\t\t text-align: center;\n" +
                "\t }\n" +
                "\t .adiv>a{\n" +
                "\t\t\tmargin-left: 10px;\n" +
                "\t\t\tmargin-right: 10px;\n" +
                "\t }\n" +
                "\t.topdiv{\n" +
                "\t\tline-height: 100px;\n" +
                "\t\twidth: 100%;\n" +
                "\t\theight: 100px;\n" +
                "\t\tbackground-color: #03b8cc;\n" +
                "\t\tFONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t}\n" +
                "\t .topdiv>span{\n" +
                "\t\t FONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t COLOR: #ffffff;\n" +
                "\t\t FONT-SIZE: 18px;\n" +
                "\t\t MARGIN-RIGHT: 20px;\n" +
                "\t\t float: right;\n" +
                "\t }\n" +
                "\t .zhengwen{\n" +
                "\t\t padding: 20px 40px;\n" +
                "\t\t font-family: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t line-height: 33px;\n" +
                "\t\t font-size: 16px;\n" +
                "\t }\n" +
                "\t .jihuo{\n" +
                "\t\t margin-top:20px\n" +
                "\t }\n" +
                "\t .jihuo>a{\n" +
                "\t\t width: 150px;height: 45px;    color: #FFFFFF;\n" +
                "\t\t background: #f97252;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t display: block;\n" +
                "\t\t line-height: 45px;\n" +
                "\t\t width: 150px;\n" +
                "\t\t text-align: center;\n" +
                "\t\t text-decoration: none;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                " </style>\n" +
                "  <div class='adiv'>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>好订货官网</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>了解好订货</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>如何购买</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>关于我们</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>联系我们</a>\n" +
                "  </div>\n" +
                "  <div class='maindiv'>\n" +
                "\t\t<div class=\"topdiv\">\n" +
                "\t\t\t<span>企业专属的移动订货客户系统</span>\n" +
                "\t\t</div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  亲爱的 "+name+"：\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  您好，欢迎您成为易订货注册用户！\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  什么是易订货？——易订货是国内新商业软件公司的代表，深圳铱云科技旗下品牌，是中国第一款移动客户订货系统。它围绕品牌企业与下游客户的全渠道业务流程设计，以订单处理为核心，帮助企业快速构建专属的全渠道营销订货平台。易订货可以帮助企业实现在商机管理、分销管控、商品促销、订单处理、库存采购、资金对账、支付物流、决策分析等业务环节的全程电子商务；实时数据决策，让生意更简单\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  您可以通过手机、平板、PC及微信登录易订货，通过这个新商业软件，为企业搭建一个专属的客户订单协作系统，将企业与下游客户之间的业务往来全程电子商务化。\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  作为中国移动客户订货系统第一品牌，易订货已经连续三年App下载量和活跃度名列行业第一，我们是中国B2b移动订货品类的开创者和领导者！\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  我们认为：方向大于努力。在移动互联网时代，传统套装ERP失去了时代的魅力，为企业生意流程服务的新一代的商业软件将更好的为客户服务，让生意更简单！\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  时代在变！我们也看到新一代的商业领袖不再墨守陈规。我们很多付费客户都是细分领域的模式创新者。他们雄心勃勃，卓尔不群、他们富有远见和野望、他们敬畏并尊崇信息技术的商业力量、他们心中渴望有属于自己的一个全渠道营销订货平台，一个以订单链接一切稳定客户关系的营销平台。这是易订货真正的产品价值，而不仅是一个订单处理系统。\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  软件就是服务。作为云时代新商业软件的优质服务品牌，我们率先在行业内开通了7*12小时不间断的在线服务，无论是节假日还是周末，易订货服务团队都会为付费客户提供优质的产品服务体验。\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  我们连续3年荣获了“2014-2016最佳企业移动订货平台”殊荣；2017年荣登中国B2b行业百强榜及中国快销品B2b行业推荐SaaS服务商；荣获ISO27001信息安全管理体系认证；荣获国家级高新技术企业认证；是供应链云平台领域内唯一连续五轮获得千万级美金最大融资额的公司。被李开复博士誉为最有“独角兽潜力”的SaaS公司。铱云科技与阿里云、钉钉、微信、用友畅捷通、系列银行支付公司形成了紧密的战略联盟合作，推出“易订货钉钉”专版，可实现与微信服务号衔接，与各品牌ERP产品实现数据无缝隙衔接，与银行和支付公司携手提供移动支付功能，帮助企业实现全渠道营销过程的电子商务化。我们在海外的用户也已分布全球各地，为此我们提供了易订货英文版插件，方便国外客户使用。\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  这是一个颠覆的时代，是到了告别传统渠道销售模式，告别依赖销售员维系客户关系手段的时刻了！\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  世界很精彩，我们足够年轻。让我们一起拥抱这个变化的移动互联网时代！\n" +
                "\t  </div>\n" +
                "\t  <div class='jihuo'>\n" +
                "\t\t  <a href='"+href+"'>点击账号激活</a>\n" +
                "\t  </div>\n" +
                "\n" +
                "  </div>\n";
    }

    public String getHtmlContent(){
        return htmlContent;
    }

    public void setFindPassword(String name,String href){
        findPassword = " <style>\n" +
                "\t .maindiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 1159px;\n" +
                "\t\t border-left: 1px solid #e3e3e3;\n" +
                "\t\t border-right: 1px solid #e3e3e3;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                "\t .adiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 40px;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t\t margin-bottom: 10px;\n" +
                "\t\t margin-top: 20px;\n" +
                "\t\t line-height: 40px;\n" +
                "\t\t text-align: center;\n" +
                "\t }\n" +
                "\t .adiv>a{\n" +
                "\t\t\tmargin-left: 10px;\n" +
                "\t\t\tmargin-right: 10px;\n" +
                "\t }\n" +
                "\t.topdiv{\n" +
                "\t\tline-height: 100px;\n" +
                "\t\twidth: 100%;\n" +
                "\t\theight: 100px;\n" +
                "\t\tbackground-color: #03b8cc;\n" +
                "\t\tFONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t}\n" +
                "\t .topdiv>span{\n" +
                "\t\t FONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t COLOR: #ffffff;\n" +
                "\t\t FONT-SIZE: 18px;\n" +
                "\t\t MARGIN-RIGHT: 20px;\n" +
                "\t\t float: right;\n" +
                "\t }\n" +
                "\t .zhengwen{\n" +
                "\t\t padding: 20px 40px;\n" +
                "\t\t font-family: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t line-height: 33px;\n" +
                "\t\t font-size: 16px;\n" +
                "\t }\n" +
                "\t .jihuo{\n" +
                "\t\t margin-top:20px\n" +
                "\t }\n" +
                "\t .jihuo>a{\n" +
                "\t\t width: 150px;height: 45px;    color: #FFFFFF;\n" +
                "\t\t background: #f97252;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t display: block;\n" +
                "\t\t line-height: 45px;\n" +
                "\t\t width: 150px;\n" +
                "\t\t text-align: center;\n" +
                "\t\t text-decoration: none;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                " </style>\n" +
                "  <div class='adiv'>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>校园二手平台官网</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>了解校园二手平台</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>如何购买</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>关于我们</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>联系我们</a>\n" +
                "  </div>\n" +
                "  <div class='maindiv'>\n" +
                "\t\t<div class=\"topdiv\">\n" +
                "\t\t\t<span>校园二手交易平台</span>\n" +
                "\t\t</div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  亲爱的 "+name+"：您的信息已核实\n" +
                "\t  </div>\n" +
                "\t  <div class='jihuo'>\n" +
                "\t\t  <a href='"+href+"' style=''>点击修改密码</a>\n" +
                "\t  </div>\n" +
                "\n" +
                "  </div>";
    }
    public String getFindPassword(){
        return findPassword;
    }

    public void setHappyBorthday(String name){
        happyBorthday=" <style>\n" +
                "\t .maindiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 1159px;\n" +
                "\t\t border-left: 1px solid #e3e3e3;\n" +
                "\t\t border-right: 1px solid #e3e3e3;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                "\t .adiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 40px;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t\t margin-bottom: 10px;\n" +
                "\t\t margin-top: 20px;\n" +
                "\t\t line-height: 40px;\n" +
                "\t\t text-align: center;\n" +
                "\t }\n" +
                "\t .adiv>a{\n" +
                "\t\t\tmargin-left: 10px;\n" +
                "\t\t\tmargin-right: 10px;\n" +
                "\t }\n" +
                "\t.topdiv{\n" +
                "\t\tline-height: 100px;\n" +
                "\t\twidth: 100%;\n" +
                "\t\theight: 100px;\n" +
                "\t\tbackground-color: #03b8cc;\n" +
                "\t\tFONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t}\n" +
                "\t .topdiv>span{\n" +
                "\t\t FONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t COLOR: #ffffff;\n" +
                "\t\t FONT-SIZE: 18px;\n" +
                "\t\t MARGIN-RIGHT: 20px;\n" +
                "\t\t float: right;\n" +
                "\t }\n" +
                "\t .zhengwen{\n" +
                "\t\t padding: 20px 40px;\n" +
                "\t\t font-family: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t line-height: 33px;\n" +
                "\t\t font-size: 16px;\n" +
                "\t }\n" +
                "\t .jihuo{\n" +
                "\t\t margin-top:20px\n" +
                "\t }\n" +
                "\t .jihuo>a{\n" +
                "\t\t width: 150px;height: 45px;    color: #FFFFFF;\n" +
                "\t\t background: #f97252;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t display: block;\n" +
                "\t\t line-height: 45px;\n" +
                "\t\t width: 150px;\n" +
                "\t\t text-align: center;\n" +
                "\t\t text-decoration: none;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                " </style>\n" +
                "  <div class='adiv'>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>好订货官网</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>了解好订货</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>如何购买</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>关于我们</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>联系我们</a>\n" +
                "  </div>\n" +
                "  <div class='maindiv'>\n" +
                "\t\t<div class=\"topdiv\">\n" +
                "\t\t\t<span>企业专属的移动订货客户系统</span>\n" +
                "\t\t</div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  亲爱的 "+name+"：\n" +
                "\t\t \t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  微信或长或短，总代表我最真挚的祝福；祝福或多或少，始终是我对你最贴心的关怀；今天是你生日，那么就送上我最朴实的祝福吧：愿你越来越漂亮，越活越年轻！\n" +
                "\t  </div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  好订货祝您生日快乐 阖家欢乐\n" +
                "\t  </div>\n" +
                "\n" +
                "  </div>";
    }
    public String getHappyBorthday(){
        return happyBorthday;
    }


    public void setValidateCode(String name,String code){
        this.validateCode = " <style>\n" +
                "\t .maindiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 1159px;\n" +
                "\t\t border-left: 1px solid #e3e3e3;\n" +
                "\t\t border-right: 1px solid #e3e3e3;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                "\t .adiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 40px;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t\t margin-bottom: 10px;\n" +
                "\t\t margin-top: 20px;\n" +
                "\t\t line-height: 40px;\n" +
                "\t\t text-align: center;\n" +
                "\t }\n" +
                "\t .adiv>a{\n" +
                "\t\t\tmargin-left: 10px;\n" +
                "\t\t\tmargin-right: 10px;\n" +
                "\t }\n" +
                "\t.topdiv{\n" +
                "\t\tline-height: 100px;\n" +
                "\t\twidth: 100%;\n" +
                "\t\theight: 100px;\n" +
                "\t\tbackground-color: #03b8cc;\n" +
                "\t\tFONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t}\n" +
                "\t .topdiv>span{\n" +
                "\t\t FONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t COLOR: #ffffff;\n" +
                "\t\t FONT-SIZE: 18px;\n" +
                "\t\t MARGIN-RIGHT: 20px;\n" +
                "\t\t float: right;\n" +
                "\t }\n" +
                "\t .zhengwen{\n" +
                "\t\t padding: 20px 40px;\n" +
                "\t\t font-family: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t line-height: 33px;\n" +
                "\t\t font-size: 16px;\n" +
                "\t }\n" +
                "\t .jihuo{\n" +
                "\t\t margin-top:20px\n" +
                "\t }\n" +
                "\t .jihuo>a{\n" +
                "\t\t width: 150px;height: 45px;    color: #FFFFFF;\n" +
                "\t\t background: #f97252;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t display: block;\n" +
                "\t\t line-height: 45px;\n" +
                "\t\t width: 150px;\n" +
                "\t\t text-align: center;\n" +
                "\t\t text-decoration: none;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                " </style>\n" +
                "  <div class='adiv'>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>好订货官网</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>了解好订货</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>如何购买</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>关于我们</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>联系我们</a>\n" +
                "  </div>\n" +
                "  <div class='maindiv'>\n" +
                "\t\t<div class=\"topdiv\">\n" +
                "\t\t\t<span>企业专属的移动订货客户系统</span>\n" +
                "\t\t</div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  亲爱的 "+name+"：\n" +
                "\t\t \t  </div>\n" +
                "\t  <div class='jihuo'>\n" +
                "\t\t  <a href='#' style=''>验证码为："+code+"</a>\n" +
                "\t  </div>\n" +
                "\n" +
                "  </div>";;
    }
    public String getValidateCode(){
        return validateCode;
    }

    public void setXiadan(String name,String kehuname,String dingdan){
            this.xiadan = " <style>\n" +
                    "\t .maindiv{\n" +
                    "\t\t width: 738px;\n" +
                    "\t\t height: 1159px;\n" +
                    "\t\t border-left: 1px solid #e3e3e3;\n" +
                    "\t\t border-right: 1px solid #e3e3e3;\n" +
                    "\t\t margin-left: auto;\n" +
                    "\t\t margin-right: auto;\n" +
                    "\t }\n" +
                    "\t .adiv{\n" +
                    "\t\t width: 738px;\n" +
                    "\t\t height: 40px;\n" +
                    "\t\t margin-left: auto;\n" +
                    "\t\t margin-right: auto;\n" +
                    "\t\t margin-bottom: 10px;\n" +
                    "\t\t margin-top: 20px;\n" +
                    "\t\t line-height: 40px;\n" +
                    "\t\t text-align: center;\n" +
                    "\t }\n" +
                    "\t .adiv>a{\n" +
                    "\t\t\tmargin-left: 10px;\n" +
                    "\t\t\tmargin-right: 10px;\n" +
                    "\t }\n" +
                    "\t.topdiv{\n" +
                    "\t\tline-height: 100px;\n" +
                    "\t\twidth: 100%;\n" +
                    "\t\theight: 100px;\n" +
                    "\t\tbackground-color: #03b8cc;\n" +
                    "\t\tFONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                    "\t}\n" +
                    "\t .topdiv>span{\n" +
                    "\t\t FONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                    "\t\t COLOR: #ffffff;\n" +
                    "\t\t FONT-SIZE: 18px;\n" +
                    "\t\t MARGIN-RIGHT: 20px;\n" +
                    "\t\t float: right;\n" +
                    "\t }\n" +
                    "\t .zhengwen{\n" +
                    "\t\t padding: 20px 40px;\n" +
                    "\t\t font-family: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                    "\t\t font-size: 16px;\n" +
                    "\t\t line-height: 33px;\n" +
                    "\t\t font-size: 16px;\n" +
                    "\t }\n" +
                    "\t .jihuo{\n" +
                    "\t\t margin-top:20px\n" +
                    "\t }\n" +
                    "\t .jihuo>a{\n" +
                    "\t\t width: 150px;height: 45px;    color: #FFFFFF;\n" +
                    "\t\t background: #f97252;\n" +
                    "\t\t font-size: 16px;\n" +
                    "\t\t display: block;\n" +
                    "\t\t line-height: 45px;\n" +
                    "\t\t width: 150px;\n" +
                    "\t\t text-align: center;\n" +
                    "\t\t text-decoration: none;\n" +
                    "\t\t margin-left: auto;\n" +
                    "\t\t margin-right: auto;\n" +
                    "\t }\n" +
                    " </style>\n" +
                    "  <div class='adiv'>\n" +
                    "\t  <a  href='http://172.16.9.1:8080'>好订货官网</a>\n" +
                    "\t  <a  href='http://172.16.9.1:8080'>了解好订货</a>\n" +
                    "\t  <a  href='http://172.16.9.1:8080'>如何购买</a>\n" +
                    "\t  <a  href='http://172.16.9.1:8080'>关于我们</a>\n" +
                    "\t  <a  href='http://172.16.9.1:8080'>联系我们</a>\n" +
                    "  </div>\n" +
                    "  <div class='maindiv'>\n" +
                    "\t\t<div class=\"topdiv\">\n" +
                    "\t\t\t<span>企业专属的移动订货客户系统</span>\n" +
                    "\t\t</div>\n" +
                    "\t  <div class='zhengwen'>\n" +
                    "\t\t  亲爱的 "+name+"：\n" +
                    "\t\t \t  </div>\n" +
                    "\t  <div class='jihuo'>\n" +
                    "\t\t  <a href='#' style=''>您的客户："+kehuname+"下单啦，及时处理哟单号为"+dingdan+"</a>\n" +
                    "\t  </div>\n" +
                    "\n" +
                    "  </div>";
        }

        public String getXiadan(){
        return  xiadan;
        }


        public void setShenhe(String name,String dingdan,String shenheliucheng){
        this.shenhe = " <style>\n" +
                "\t .maindiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 1159px;\n" +
                "\t\t border-left: 1px solid #e3e3e3;\n" +
                "\t\t border-right: 1px solid #e3e3e3;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                "\t .adiv{\n" +
                "\t\t width: 738px;\n" +
                "\t\t height: 40px;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t\t margin-bottom: 10px;\n" +
                "\t\t margin-top: 20px;\n" +
                "\t\t line-height: 40px;\n" +
                "\t\t text-align: center;\n" +
                "\t }\n" +
                "\t .adiv>a{\n" +
                "\t\t\tmargin-left: 10px;\n" +
                "\t\t\tmargin-right: 10px;\n" +
                "\t }\n" +
                "\t.topdiv{\n" +
                "\t\tline-height: 100px;\n" +
                "\t\twidth: 100%;\n" +
                "\t\theight: 100px;\n" +
                "\t\tbackground-color: #03b8cc;\n" +
                "\t\tFONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t}\n" +
                "\t .topdiv>span{\n" +
                "\t\t FONT-FAMILY: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t COLOR: #ffffff;\n" +
                "\t\t FONT-SIZE: 18px;\n" +
                "\t\t MARGIN-RIGHT: 20px;\n" +
                "\t\t float: right;\n" +
                "\t }\n" +
                "\t .zhengwen{\n" +
                "\t\t padding: 20px 40px;\n" +
                "\t\t font-family: 'Open Sans', Arial, 'Hiragino Sans GB', 'Microsoft YaHei', 微软雅黑, STHeiti, 'WenQuanYi Micro Hei', SimSun, sans-serif;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t line-height: 33px;\n" +
                "\t\t font-size: 16px;\n" +
                "\t }\n" +
                "\t .jihuo{\n" +
                "\t\t margin-top:20px\n" +
                "\t }\n" +
                "\t .jihuo>a{\n" +
                "\t\t width: 150px;height: 45px;    color: #FFFFFF;\n" +
                "\t\t background: #f97252;\n" +
                "\t\t font-size: 16px;\n" +
                "\t\t display: block;\n" +
                "\t\t line-height: 45px;\n" +
                "\t\t width: 150px;\n" +
                "\t\t text-align: center;\n" +
                "\t\t text-decoration: none;\n" +
                "\t\t margin-left: auto;\n" +
                "\t\t margin-right: auto;\n" +
                "\t }\n" +
                " </style>\n" +
                "  <div class='adiv'>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>好订货官网</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>了解好订货</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>如何购买</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>关于我们</a>\n" +
                "\t  <a  href='http://172.16.9.1:8080'>联系我们</a>\n" +
                "  </div>\n" +
                "  <div class='maindiv'>\n" +
                "\t\t<div class=\"topdiv\">\n" +
                "\t\t\t<span>企业专属的移动订货客户系统</span>\n" +
                "\t\t</div>\n" +
                "\t  <div class='zhengwen'>\n" +
                "\t\t  亲爱的 "+name+"：\n" +
                "\t\t \t  </div>\n" +
                "\t  <div class='jihuo'>\n" +
                "\t\t  <a href='#' style=''>您的订单："+dingdan+"已通过"+shenheliucheng+"快去查看吧</a>\n" +
                "\t  </div>\n" +
                "\n" +
                "  </div>";
        }
        public String getShenhe(){
        return shenhe;
        }
    }

