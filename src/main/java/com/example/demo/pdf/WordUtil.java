package com.example.demo.pdf;

import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Format;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;
import java.util.Base64;

public class WordUtil {

    /**
     * 生成File对象
     * @param dataMap
     * @param templateName
     * @return
     */
    public static File createDoc(Map dataMap, String templateName)
    {

        String fileName = "temp" + "test" + ".doc";
        File f = new File(fileName);

        //创建配置实例
        Configuration configuration = new Configuration();

        //设置编码
        configuration.setDefaultEncoding("UTF-8");

        //ftl模板文件
        configuration.setClassForTemplateLoading(WordUtil.class, "/templates/");

        try{
            //获取模板
            Template template = configuration.getTemplate(templateName);

            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8));

            //生成文件
            template.process(dataMap, out);

            //关闭流
            out.flush();
            out.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return f;
    }

    public static void createWord(Map dataMap, String templateName, String filePath, String fileName) {
        try {
            //创建配置实例
            Configuration configuration = new Configuration();

            //设置编码
            configuration.setDefaultEncoding("UTF-8");

            //ftl模板文件
            configuration.setClassForTemplateLoading(WordUtil.class, "/templates");
//            configuration.setDirectoryForTemplateLoading(new File("D://uploadFiles//template//"));
            //获取模板
            Template template = configuration.getTemplate(templateName);

            //输出文件
            File outFile = new File(filePath + File.separator + fileName);

            //如果输出目标文件夹不存在，则创建
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }

            //将模板和数据模型合并生成文件
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), StandardCharsets.UTF_8));


            //生成文件
            template.process(dataMap, out);

            //关闭流
            out.flush();
            out.close();
        } catch (Exception e) {
            Logger.getLogger("TicketController").info(e.toString());
            e.printStackTrace();
        }
    }


    /**
     * 将图片转换为BASE64为字符串
     * @param filename
     * @return
     * @throws IOException
     */
    public static String getImageString(String filename) throws IOException {
        if(!new File(filename).exists()){//文件不存在
            return "";
        }
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(filename);
//            in = new ClassPathResource("static/select_true.png").getInputStream();
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if(in != null) in.close();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }


    /**
     * 将图片转换为BASE64为字符串
     * @param in
     * @return
     * @throws IOException
     */
    public static String getImageString(InputStream in) throws IOException {
//        InputStream in = null;
        byte[] data = null;
        try {
//            in = new FileInputStream("D://uploadFiles//doc//11111.png");
//            in = new ClassPathResource("static/select_true.png").getInputStream();
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if(in != null) in.close();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }

}
