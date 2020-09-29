package com.example.demo.officialAccounts;

import org.springframework.lang.NonNull;
import scala.util.parsing.combinator.testing.Str;

public class urlUtils {

    private static String str = "";

    // 获取接口调用凭证
    private static String  getAccessToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static String getGetAccessToken(@NonNull String APPID, @NonNull String APPSECRET) {
        return getAccessToken.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
    }


    public static String getSetIndustry(@NonNull String ACCESS_TOKEN) {
        return setIndustry.replace("ACCESS_TOKEN", ACCESS_TOKEN);
    }

    // 设置所属行业
    private static String setIndustry = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";

    // 获取设置的行业
    private static String getIndustry = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    public static String getIndustry(@NonNull String ACCESS_TOKEN) {
        return getIndustry.replace("ACCESS_TOKEN", ACCESS_TOKEN);
    }


    public static String getAddTemplate(@NonNull String ACCESS_TOKEN) {
        return addTemplate.replace("ACCESS_TOKEN", ACCESS_TOKEN);
    }

    // 获取行业消息模板
    private static String addTemplate = " https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";

    public static String getGetAllTemplate(@NonNull String ACCESS_TOKEN) {
        return getAllTemplate.replace("ACCESS_TOKEN", ACCESS_TOKEN);
    }

    // 获取模板列表
    private static String getAllTemplate = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";

    public static String getSendMessage(@NonNull String ACCESS_TOKEN) {
        return sendMessage.replace("ACCESS_TOKEN", ACCESS_TOKEN);
    }

    // 发送模板消息
    private static String sendMessage = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";

    public static String getGetUserList(@NonNull String ACCESS_TOKEN, String NEXT_OPENID) {
        return getUserList.replace("ACCESS_TOKEN", ACCESS_TOKEN).replace("NEXT_OPENID", NEXT_OPENID);
    }

    // 获取用户信息
    private static String getUserList = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

    public static String getSendaAll(@NonNull String ACCESS_TOKEN) {
        return sendaAll.replace("ACCESS_TOKEN", ACCESS_TOKEN);
    }

    // 根据标签群发,或者全员发送
    private static String sendaAll = " https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

    public static String getSendAllByOpenId(@NonNull String ACCESS_TOKEN) {
        return sendAllByOpenId.replace("ACCESS_TOKEN", ACCESS_TOKEN);
    }

    // 根据openid群发
    private static String sendAllByOpenId = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";


    public static String getGetUserInfo(@NonNull String ACCESS_TOKEN, @NonNull String OPENID) {
        return getUserInfo.replace("ACCESS_TOKEN", ACCESS_TOKEN).replace("OPENID", OPENID);
    }

    // 获取用户信息详情
    private static String getUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

}
