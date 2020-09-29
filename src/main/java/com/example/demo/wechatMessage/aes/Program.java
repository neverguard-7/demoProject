package com.example.demo.wechatMessage.aes;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.boot.json.JsonParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



public class Program {

	public static void main(String[] args) throws Exception {
//		subscribeMessage();
		//
		// 第三方回复公众平台
		//

		// 需要加密的明文
		String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";
		String token = "pamtest";
		String timestamp = "1409304348";
		String nonce = "xxxxxx";
		String appId = "wx7b4690a4915b8711";
		String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";

		WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
		String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
		System.out.println("加密后: " + mingwen);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(mingwen);
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);

		Element root = document.getDocumentElement();
		NodeList nodelist1 = root.getElementsByTagName("Encrypt");
		NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

		String encrypt = nodelist1.item(0).getTextContent();
		String msgSignature = nodelist2.item(0).getTextContent();

		String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
		String fromXML = String.format(format, encrypt);

		//
		// 公众平台发送消息给第三方，第三方处理
		//

		// 第三方收到公众号平台发送的消息
		String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
		System.out.println("解密后明文: " + result2);
		
		//pc.verifyUrl(null, null, null, null);
	}


	@Test
	public  void  test()
	{
//		String result = httpFunction.sendPostRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7b4690a4915b8711&secret=6567d553c03ddb42abab0d764942b970", "");
		String result = httpFunction.sendPostRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxe3b5efa6b089deb3&secret=de42a4d8c2fbb89d529ca2b4f0b39573", "");

//		String result = httpFunction.sendPostRequest("https://api.weixin.qq.com/cgi-bin/message/wxopen/template/uniform_send?access_token=ACCESS_TOKEN", "");
		System.out.println(result);
	}

	@Test
	public  void subscribeMessage()
	{

		JSONObject jsonObject1 = new JSONObject();
		jsonObject1.put("","");

//		JSONObject jsonObject = new JSONObject();
//		jsonObject.put("access_token", str);
//		jsonObject.put("miniprogram_state", "developer");
//		jsonObject.put("touser", "o8l0546R-intlK5W-z-UlqrAHi-k");
//		jsonObject.put("template_id", "UTIqILfnEjYeyuvvjBMvO0C1W2PMsWdFqf31C8ziuDw");
//		jsonObject.put("data", JSONObject.parse("{\"data\":{\"thing1\":\"339208499\"},\"thing2\":{\"value\":\"2015年01月05日\"},\"time3\":{\"value\":\"2015年01月05日\"}}"));
//		jsonObject.put("data", JSONObject.parse("{\"thing1\":\"339208499\",\"thing2\":{\"value\":\"2015年01月05日\"}}"));

		String strPaser = "{\n" +
				"  \"touser\": \"o8l0546R-intlK5W-z-UlqrAHi-k\",\n" +
				"  \"template_id\": \"UTIqILfnEjYeyuvvjBMvO0C1W2PMsWdFqf31C8ziuDw\",\n" +
				"  \"miniprogram_state\":\"developer\",\n" +
				"  \"lang\":\"zh_CN\",\n" +
				"  \"data\": {\n" +
				"      \"thing1\": {\n" +
				"          \"value\": \"339208499\"\n" +
				"      },\n" +
				"      \"thing2\": {\n" +
				"          \"value\": \"2015年01月05日\"\n" +
				"      },\n" +
				"      \"time3\": {\n" +
				"          \"value\": \"2015年01月05日\"\n" +
				"      } \n" +
				"  }\n" +
				"}";
		System.out.println(str);
		String result = httpFunction.sendPostRequest("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" +str, strPaser);

//		System.out.println(jsonObject.get("data"));
		System.out.println(result);
	}

	private static final String str= "36_CBYfu3fLy2g76w1Kz-QhnEjEpJgQ2aXsa_xkew90HW5neRKkwSQipMfaacD_XRMBIZ3G7cTuT8nQRqMS9njsEuEX7LFWPRGXbuKwCi0in4ZZqrjM31Gi3k3JzfoXx8adlqtV7Jn6n34PqYAjBNXbAHASEO";

	@Test
	public void getTemp()
	{

		String result = httpFunction.sendGetRequest("https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=" + str);

//		String result1 = httpFunction.sendGetRequest("https://api.weixin.qq.com/wxaapi/newtmpl/getpubtemplatetitles?access_token=" + str + "&" + "ids=2&start=0&limit=10");
		System.out.println(result);
//		System.out.println(result1);
	}

	public  void addTemp()
	{
		String result = httpFunction.sendPostRequest("https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate?access_token=" + str, "");
		System.out.println(result);
	}
}
