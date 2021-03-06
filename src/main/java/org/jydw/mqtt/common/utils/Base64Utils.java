package org.jydw.mqtt.common.utils;

import com.github.pagehelper.util.StringUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName Base64Utils
 * @Description TODO
 * @Date 2019/6/12 16:41
 * @Author fankai
 * @Version 1.0
 **/
public class Base64Utils {

    public static void main(String[] args) throws Exception {

        //本地图片地址
        String url = "C:/Users/Administrator/Desktop/628947887489084892.jpg";
        //在线图片地址
        String string = "http://bpic.588ku.com//element_origin_min_pic/17/03/03/7bf4480888f35addcf2ce942701c728a.jpg";

        String str = Base64Utils.imagetobase64bylocal(url);

        String ste = Base64Utils.imagetobase64byonline(string);

        System.out.println(str);

        Base64Utils.base64toimage(str,"C:/bigdata/base64/test1.jpg");

        Base64Utils.base64toimage(ste, "C:/bigdata/base64/test2.jpg");
    }

    /**
     * 本地图片转换成base64字符串
     * @Description 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @param imgFile	图片本地路径
     * @return String
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:40:46
     */
    public static String imagetobase64bylocal(String imgFile) {


        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }



    /**
     * 在线图片转换成base64字符串
     *
     * @param imgURL	图片线上路径
     * @return
     *
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:43:18
     */
    public static String imagetobase64byonline(String imgURL) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            // 创建URL
            URL url = new URL(imgURL);
            byte[] by = new byte[1024];
            // 创建链接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }


    /**
     * base64字符串转换成图片
     * @Description 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr        base64字符串
     * @param imgFilePath    图片存放路径
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:42:17
     */
    public static void base64toimage(String imgStr, String imgFilePath) {

        // 图像数据为空
        if (StringUtil.isEmpty(imgStr)) {
            return;
        }

        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();

        } catch (Exception e) {
        }

    }
}
