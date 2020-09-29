package com.example.demo.sendMessage;


public class  UrlInfo {

    // 获取模板
    public static  String getTemplates = "https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=";

    // 设置模板
    public static  String setTemplates = "https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate?access_token=";

    // 发送订阅消息
    public static  String subscribeMessage = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";


    public static String getGetAccessToken(String APPID, String APPSECRET) {
        return getAccessToken.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
    }

    // 获取接口调用凭证
    private static String  getAccessToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static String getUniformSend(String str) {
        return uniformSend.replace("ACCESS_TOKEN", str);
    }

    // 统一服务消息url
    private static  String  uniformSend = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN";

}
