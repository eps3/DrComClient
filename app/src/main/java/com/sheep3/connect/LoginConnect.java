package com.sheep3.connect;


import com.sheep3.content.Content;
import com.sheep3.utils.MD5Util;
import com.sheep3.utils.StreamTool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 社ep
 * @create 2016--06--16--14:45
 */
public class LoginConnect {
    public static final String PID = "1";
    public static final String CALG = "12345678";

    public static int FLUX_KB;
    public static int FLUX_MB;
    public static String FLUX_Result;



    public static int TIME;
    public static String TIME_Result;

    public static boolean quit() throws Exception {
        URL url =new URL(Content.QUIT_NETWORK);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setConnectTimeout(5*1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        byte[] data = StreamTool.inputStream2Byte(inStream);
        String result=new String(data, "UTF-8");
        //System.out.println(result);
        return result.contains("Msg=14");
    }

    public static void getMessage() throws Exception {
        URL url =new URL(Content.NETWORK);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setReadTimeout(5*1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        byte[] data = StreamTool.inputStream2Byte(inStream);
        String result=new String(data, "UTF-8");
        String[] split = result.split("'");
        //计算流量
        try {
            FLUX_KB = Integer.parseInt(split[3].trim());
        }catch (Exception e){
            FLUX_KB= 0;
        }
        FLUX_MB = FLUX_KB/1024;
        int FLUX_GB = FLUX_MB/1024;
        FLUX_Result = FLUX_GB+"G "+(FLUX_MB-FLUX_GB*1024)+"MB "+(FLUX_KB-FLUX_MB*1024)+"KB";

        // 计算时间
        try {
            TIME = Integer.parseInt(split[1].trim());
        }catch (Exception e){
            TIME= 0;
        }
        int h = TIME/60 ;
        int d = h/24;
        TIME_Result =d+"天 "+ (h - d*24)+"小时 "+(TIME - h*60)+"分钟";
    }

    public static boolean login(String name, String pwd) throws Exception {
        String encoding="UTF-8";
        String params="DDDDD="+name+
                "&upass="+(MD5Util.MD5(PID+pwd+CALG)+CALG+PID)+
                "&R1=0&R2=1&para=00&0MKKey=123456";
        String path =Content.NETWORK;
        byte[] data = params.getBytes(encoding);
        URL url =new URL(path);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset="+ encoding);
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));
        conn.setConnectTimeout(5*1000);
        conn.setReadTimeout(5*1000);
        OutputStream outStream = conn.getOutputStream();
        outStream.write(data);
        outStream.flush();
        outStream.close();
        System.out.println(conn.getResponseCode()); //响应代码 200表示成功
        if(conn.getResponseCode()==200){
            InputStream inStream = conn.getInputStream();
            String result=new String(StreamTool.inputStream2Byte(inStream), "UTF-8");
            return result.contains(name);
        }
        return false;
    }

}
