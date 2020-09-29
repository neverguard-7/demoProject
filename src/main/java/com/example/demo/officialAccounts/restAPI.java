package com.example.demo.officialAccounts;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import com.example.demo.wechatMessage.aes.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/demo")
public class restAPI {


//    @Autowired
//    foRestInterface foRestInterface;

    @Autowired
    businessImpl business;


    /**
     * 响应微信服务器
     * @param request
     * @return
     */
    @RequestMapping(value = "/responeWechatServer")
    public String responeWechatServer (HttpServletRequest request) throws IOException {
        boolean isGet = request.getMethod().toLowerCase().equals("get");

        if(isGet)
        {
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            System.out.println(signature);
            System.out.println(timestamp);
            System.out.println(nonce);
            System.out.println(echostr);
            try {
                String res = SHA1.getSHA1("123456",timestamp,nonce, "");
                System.out.println(res.equals(signature));
            } catch (AesException e) {
                e.printStackTrace();
            }
            return echostr;
        }
        else
        {
            // 处理接收消息
            ServletInputStream in = request.getInputStream();
            StringBuilder xmlMsg = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = in.read(b)) != -1;) {
                xmlMsg.append(new String(b, 0, n, "UTF-8"));
            }

            System.out.println(xmlMsg);

        }
        return "";
    }

//    @RequestMapping(value = "/responeWechatServer", method = RequestMethod.POST)
//    public String responeWechatServer (HttpServletRequest request)
//    {
//        String signature = request.getParameter("signature");
//        String timestamp = request.getParameter("timestamp");
//        String nonce = request.getParameter("nonce");
//        String echostr = request.getParameter("echostr");
//        System.out.println(signature);
//        System.out.println(timestamp);
//        System.out.println(nonce);
//        System.out.println(echostr);
//        try {
//            String res = SHA1.getSHA1("123456",timestamp,nonce, "");
//            System.out.println(res.equals(signature));
//        } catch (AesException e) {
//            e.printStackTrace();
//        }
//        return echostr;
//    }

    private static String str = "37_BrKPMYK1uKEEe4jt5DUxFQUix17_EUWr6t6nhZZvsPpwtPqcnga0akBcHivLDfIijxi9EmAyEOlg13QRL_4rqAkPy34OVxZbI5kDmngxX17RzdQ3ggB0tAxdiaiz1N5rwm2vfuOGczTpPLbVDSTdAHAKZA";


    @Test
    public  void  getAccessToken()
    {
        String result = httpFunction.sendPostRequest(urlUtils.getGetAccessToken("wx801b7f023135f99e","059746d07cbf62951e8a33a285d029ab"), "");
        System.out.println(result);
    }



    // 设置行业
    public void setIndustry()
    {
        String query = "{\n" +
                "    \"industry_id1\":\"2\",\n" +
                "    \"industry_id2\":\"3\"\n" +
                "}";
        String result = httpFunction.sendPostRequest(urlUtils.getSetIndustry(str), query);
        System.out.println(result);
    }

    // 获取行业
    @Test
    public void getIndustry()
    {
        String result = httpFunction.sendGetRequest(urlUtils.getIndustry(str));
        System.out.println(result);
    }

    // 获取行业消息模板
    @Test
    public void addTemplate()
    {
        String query = "{\n" +
                "    \"template_id_short\":\"pxTeZKXX2FTxmKbR1adtckcw9sRvEobDbXoeEI7Kng8\"\n" +
                " }";
        String result = httpFunction.sendPostRequest(urlUtils.getAddTemplate(str), query);
        System.out.println(result);
    }


    // 获取已配置的所有模板
    @Test
    public void getAllTemp()
    {
        String result = httpFunction.sendGetRequest(urlUtils.getGetAllTemplate(str));
        System.out.println(result);
    }

    // 获取用户
    @Test
    public void getUserList()
    {
        String result = httpFunction.sendGetRequest(urlUtils.getGetUserList(str, ""));
        System.out.println(result);
    }


    @Test
    public void sendMessage()
    {
        String query = "{\n" +
                "           \"touser\":\"o5rmo1NMN_dabNan5j9stti_7ZT4\",\n" +
                "           \"template_id\":\"pxTeZKXX2FTxmKbR1adtckcw9sRvEobDbXoeEI7Kng8\",      \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"恭喜你购买成功！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"test2\":{\n" +
                "                       \"value\":\"巧克力\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"欢迎再次购买！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";

        String query2 = "      {\n" +
                "           \"touser\":\"o8l0540qmBrkJByaoUa-in-0SzBg\",\n" +
                "           \"template_id\":\"pxTeZKXX2FTxmKbR1adtckcw9sRvEobDbXoeEI7Kng8\",      \n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"零食消息！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"巧克力\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "\t\t\t\t   \"keyword2\":{\n" +
                "                       \"value\":\"巧克力\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "\t\t\t\t   \"keyword3\":{\n" +
                "                       \"value\":\"巧克力\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "\t\t\t\t   \"keyword4\":{\n" +
                "                       \"value\":\"巧克力\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "\t\t\t\t   \"keyword5\":{\n" +
                "                       \"value\":\"巧克力\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"好吃不腻！\",\n" +
                "                       \"color\":\"#173177\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";
        String result = httpFunction.sendPostRequest(urlUtils.getSendMessage(str), query2);
        System.out.println(result);
    }

    @Test
    public void sendAll()
    {
        String query2 = "{\n" +
                "   \"filter\":{\n" +
                "      \"is_to_all\":true,\n" +
                "      \"tag_id\":2\n" +
                "   },\n" +
                "   \"text\":{\n" +
                "      \"content\":\"CONTENT\"\n" +
                "   },\n" +
                "    \"msgtype\":\"text\"\n" +
                "}";
        String result = httpFunction.sendPostRequest(urlUtils.getSendaAll(str), query2);
        System.out.println(result);
    }

    @Test
    public void sendAllByOpenId()
    {
        String query2 = "{\n" +
                "\t\"touser\": [\n" +
                "\t\t\"o5rmo1PNPcms1nCXamLItaGxBRJE\",\n" +
                "\t\t\t\"o5rmo1B1O8bCgenp-t3767XDLEqM\",\n" +
                "\t\t\t\"o5rmo1GzcbxDzNP6v34Dl1uC5Fh0\",\n" +
                "\t\t\t\"o5rmo1DBdiXTTXDRMWEYbY1f7ZHM\",\n" +
                "\t\t\t\"o5rmo1D9Fax4jLrpT9ggN1uakgEc\",\n" +
                "\t\t\t\"o5rmo1A9XrzlOrQjVPqTbZU_Apds\",\n" +
                "\t\t\t\"o5rmo1LtsF60aiZ-dfXX_nKsBokQ\",\n" +
                "\t\t\t\"o5rmo1AWQ0_tCpioneA-onLdN97w\",\n" +
                "\t\t\t\"o5rmo1MbHt5JEKjpdS_cpr5J2sjY\",\n" +
                "\t\t\t\"o5rmo1GelUZXyklRiouzsqC12rVc\",\n" +
                "\t\t\t\"o5rmo1CcsbQ0zEPqqY8Q3ySyOI2M\",\n" +
                "\t\t\t\"o5rmo1Oj97ey0xMi8jniwB23BxZM\",\n" +
                "\t\t\t\"o5rmo1NFjkV2cQh5WUlhEzmMFVFM\",\n" +
                "\t\t\t\"o5rmo1DO_Leo9NlDap33ucqXhsxY\",\n" +
                "\t\t\t\"o5rmo1ABl5aWGuGAtz-uE_ZFD4e8\",\n" +
                "\t\t\t\"o5rmo1BCn3Gc4eyjTFkrVQKtVJiE\",\n" +
                "\t\t\t\"o5rmo1D-i6ywZbZZ-9blzu8C5Rpc\",\n" +
                "\t\t\t\"o5rmo1EKEDW7MLZs18aYIPI8yfMo\",\n" +
                "\t\t\t\"o5rmo1AiDBd7l4X00wZn8NhUJd3M\",\n" +
                "\t\t\t\"o5rmo1OT2u2lFI1a6xBT0y2wnPS0\",\n" +
                "\t\t\t\"o5rmo1Df6T1VHhsnwqx5orxAeQnk\",\n" +
                "\t\t\t\"o5rmo1GCAc5FJmMjzgSBicryFZ-E\",\n" +
                "\t\t\t\"o5rmo1M7O_DrUwuFmqpG9A4QWYrU\",\n" +
                "\t\t\t\"o5rmo1DL5gVNe5CnviRcnw5IdwdY\",\n" +
                "\t\t\t\"o5rmo1KOEcxjDP-CuWkAt9T1nYrI\",\n" +
                "\t\t\t\"o5rmo1NMN_dabNan5j9stti_7ZT4\"\n" +
                "\t],\n" +
                "\t\"msgtype\": \"text\",\n" +
                "\t\"text\": {\n" +
                "\t\t\"content\": \"hello from boxer.\"\n" +
                "\t}\n" +
                "}";
        String query1 = "{\n" +
                "\t\"touser\": [\n" +
                "\t\t\"o5rmo1NMN_dabNan5j9stti_7ZT4\",\n" +
                "\t\t\t\"o5rmo1NMN_dabNan5j9stti_7ZT4\"\n" +
                "\t],\n" +
                "\t\"msgtype\": \"text\",\n" +
                "\t\"text\": {\n" +
                "\t\t\"content\": \"hello from boxer.\"\n" +
                "\t}\n" +
                "}";
;        String result = httpFunction.sendPostRequest(urlUtils.getSendAllByOpenId(str), query1);
        System.out.println(result);
    }

    @Test
    public void getUserInfo()
    {
        String result = httpFunction.sendGetRequest(urlUtils.getGetUserInfo(str, "o5rmo1NMN_dabNan5j9stti_7ZT4"));
//        String result = httpFunction.sendGetRequest(urlUtils.getGetUserInfo(str, "oMxkP5MzrJMB8k66LticzVcfPG_w"));
        System.out.println(result);
    }

    @Autowired
    CacheManager cacheManager;

    @GetMapping(value = "/getStr")
    @Cacheable(value = "getStr", key = "#root.methodName")
    public String getStr()
    {
        System.out.println(cacheManager.getCache("getStr"));
        Cache cache = cacheManager.getCache("getStr");
        System.out.println(cache.getNativeCache());

        return "abcde";
    }

    @GetMapping(value = "/getOut")
    public void getOut()
    {
        Cache cache = cacheManager.getCache("getStr");
        System.out.println(cache.getNativeCache());
    }
}
