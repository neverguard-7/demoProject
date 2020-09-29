//package com.example.demo;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.junit.Test;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//
//
//
//
//    public class Tests {
//        private  final String PATH = "C:/Users/Administrator/Documents/WeChat Files/wxid_s16g93ncmp2821/FileStorage/File/2020-08/设备信息3.json";
//
//        @Test
//        public  void main() throws Exception {
//            System.out.println("========== JSON ---> 转换成 SQL 开始 ==========");
//            jsonToExcel();
//            System.out.println("========== JSON ---> 转换成 SQL 结束 ==========");
//        }
//
//        private  void jsonToExcel() throws Exception {
//            JsonParser jsonParser = new JsonParser();
//            JsonArray jsonArray = (JsonArray) jsonParser.parse(new FileReader(PATH));
//            FileReader fileReader = new FileReader(PATH);
////            JsonArray jsonArray = JsonParser.p();
//            for (int i = 0; i < jsonArray.size(); i++) {
//                JsonElement jsonElement = jsonArray.get(i);
//                JsonObject featuresObj = jsonElement.getAsJsonObject();
//                String name = featuresObj.get("name").toString();
//                String pinyin = featuresObj.get("pinyin").toString();
//                String sqlStr = "insert into credit_card_city_info (id, city_name, initial,state,ishot,online_time,down_time,create_time,update_time,remark) values (null,"+name+","+pinyin+",null,null,null,null,null,null,null); \r\n";
//                System.out.println(sqlStr);
//                File file = new File("C:\\Users\\Administrator\\Documents\\WeChat Files\\wxid_s16g93ncmp2821\\FileStorage\\File\\2020-08\\设备信息3.sql");
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//                FileWriter fileWriter = new FileWriter(file, true);
//                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//                bufferedWriter.write(sqlStr);
//                bufferedWriter.close();
//            }
//        }
//    }
//
//
