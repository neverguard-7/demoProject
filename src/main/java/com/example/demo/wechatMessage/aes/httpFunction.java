package com.example.demo.wechatMessage.aes;

import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class httpFunction {

    /**
     * 发起get请求
     * @param url
     * @return
     */
    public static String sendGetRequest(String url) {
        String result = "";
        InputStream in = null;
        HttpURLConnection connection = null;

        try {
            URL httpUrl = new URL(url);
            connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == 200) {
                in = connection.getInputStream();

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                int n = 0;
                byte[] datas = new byte[2048];

                while ((n = in.read(datas)) != -1) {
                    bs.write(datas, 0, n);
                }

                bs.flush();
                result = new String(bs.toByteArray(), "utf-8");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                connection.disconnect();
            } catch (Exception ex) {
            }
        }

        return result;
    }

    /**
     * 发起post请求
     * @param url
     * @param param
     * @return
     */
    public static String sendPostRequest(String url, String param) {
        InputStream in = null;
        String result = "";
        HttpURLConnection connection = null;

        try {
            URL httpUrl = new URL(url);
            connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.getOutputStream().write(param.getBytes("utf-8"));
            connection.getOutputStream().flush();

            if (connection.getResponseCode() == 200) {
                in = connection.getInputStream();

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                int n = 0;
                byte[] datas = new byte[2048];

                while ((n = in.read(datas)) != -1) {
                    bs.write(datas, 0, n);
                }

                bs.flush();
                result = new String(bs.toByteArray(), "utf-8");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                connection.disconnect();
            } catch (Exception ex) {
            }
        }

        return result;
    }

}
