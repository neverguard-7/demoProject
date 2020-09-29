//package com.example.demo.officialAccounts;
//
//import com.dtflys.forest.annotation.DataVariable;
//import com.dtflys.forest.annotation.Query;
//import com.dtflys.forest.annotation.Request;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//
//@Component
//public interface foRestInterface {
//
//    @Request(url = "http://localhost:80/demo/responeWechatServer")
//    String simpleRequest();
//
//
//    /**
//     * 整个完整的URL都通过 @DataVariable 注解修饰的参数动态传入
//     */
//    @Request(url = "${myURL}")
//    String send1(@DataVariable("myURL") String myURL);
//
//    /**
//     * 使用 @Query 注解，可以直接将该注解修饰的参数动态绑定到请求url中
//     * 注解的 value 值即代表它在url的Query部分的参数名
//     */
//    @Request(url = "http://${myURL}/abc?id=0")
//    String send2(@Query("a") String a, @Query("b") String b);
//}
