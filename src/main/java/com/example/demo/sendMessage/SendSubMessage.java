package com.example.demo.sendMessage;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.wechatMessage.aes.httpFunction;
import org.junit.Test;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;




@Component
public class SendSubMessage {

	@Test
	public  void  getAccessToken()
	{
		String result = httpFunction.sendPostRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxe3b5efa6b089deb3&secret=de42a4d8c2fbb89d529ca2b4f0b39573", "");
//		return JSONObject.parseObject(result);
		System.out.println(result);
	}

	public void getUniformSend()
	{
		String msg = "{\"touser\":\"o8l0540qmBrkJByaoUa-in-0SzBg\",\n" +
				"    \"weapp_template_msg\":{\n" +
				"        \"template_id\":\"UTIqILfnEjYeyuvvjBMvO0C1W2PMsWdFqf31C8ziuDw\",\n" +
				"        \"page\":\"page/page/index\",\n" +
				"        \"form_id\":\"9357\",\n" +
				"        \"data\":{\n" +
				"            \"thing1\":{\n" +
				"                \"value\":\"339208499\"\n" +
				"            },\n" +
				"            \"time3\":{\n" +
				"                \"value\":\"2015年01月05日 12:30\"\n" +
				"            },\n" +
				"            \"thing2\":{\n" +
				"                \"value\":\"腾讯微信总部\"\n" +
				"            },\n" +
				"            \"keyword4\":{\n" +
				"                \"value\":\"广州市海珠区新港中路397号\"\n" +
				"            }\n" +
				"        },\n" +
				"        \"emphasis_keyword\":\"thing1.DATA\"\n" +
				"    }}";
		String result = httpFunction.sendPostRequest(UrlInfo.getUniformSend(str), "");
		System.out.println();
	}

	@Test
	public  void subscribeMessage()
	{
		// 三层json
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		JSONObject jsonObject3 = new JSONObject();
		JSONObject jsonObject4 = new JSONObject();
		JSONObject jsonObject5 = new JSONObject();


		jsonObject3.put("value","339208499");
		jsonObject4.put("value","339208499");
		jsonObject5.put("value","2015年01月05日");

		jsonObject2.put("thing1",jsonObject3);
		jsonObject2.put("thing2",jsonObject4);
		jsonObject2.put("time3",jsonObject5);


		jsonObject1.put("data",jsonObject2);
		// tasktrace  o8l0546R-intlK5W-z-UlqrAHi-k
		// o8l0540qmBrkJByaoUa-in-0SzBg
		// o8l0547sSPgy0PBAyc5tkhSh-Mps
		jsonObject1.put("touser","o8l0540qmBrkJByaoUa-in-0SzBg");
		jsonObject1.put("template_id","UTIqILfnEjYeyuvvjBMvO0C1W2PMsWdFqf31C8ziuDw");
		jsonObject1.put("lang","zh_CN");
		jsonObject1.put("miniprogram_state","developer");

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

		String result = httpFunction.sendPostRequest(UrlInfo.subscribeMessage + str, jsonObject1.toJSONString());

		System.out.println(result);

//		return JSONObject.parseObject(result);
	}

	private static final String str= "36_VAQsTbQxirIaE6twylBMWUqFZ7HfO7XbVssCHVzN-_9nKaUdHiTgXVqbA5IkTlEQTaY0PoQkJ_8sHvz_JJQr-YtcltrEfUy3AwcTHBFywY51rpFj2zjWo-yB7CRQ_cc8qwX7bXY6zq0oa-3YUGWaAHABND";

	@Test
	public void getTemp()
	{
		String result = httpFunction.sendGetRequest("https://api.weixin.qq.com/wxaapi/newtmpl/gettemplate?access_token=" + str);
		System.out.println(result);

	}

	public  void addTemp()
	{
		String result = httpFunction.sendPostRequest("https://api.weixin.qq.com/wxaapi/newtmpl/addtemplate?access_token=" + str, "");
		System.out.println(result);
	}


	public static SendSubMessage singleton;
	public static SendSubMessage with() {
		if (singleton == null) {
			synchronized (SendSubMessage.class) {
				if (singleton == null) {
					singleton = new SendSubMessage();
				}
			}
		}
		return singleton;
	}

	public SendSubMessage build() {
		return new SendSubMessage();
	}

	public void TimeTask()
	{

	}

}
