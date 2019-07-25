package org.jydw.mqtt.handler;

import com.alibaba.fastjson.JSONObject;
import org.jydw.mqtt.common.utils.Base64Utils;

import java.util.UUID;

/**
 * @ClassName TypeHandler
 * @Description TODO
 * @Date 2019/6/12 16:49
 * @Author fankai
 * @Version 1.0
 **/
public class TypeHandler {

    /**
     * @return void
     * @Description 测温数据发送(base64编码)处理
     * @Date 2019/6/12 16:50
     * @Author fankai
     * @Param object
     **/
    public static void base64Handler(JSONObject object) {
        if ("5".equals(object.getString("ProtocolType"))) {
            Base64Utils.base64toimage(object.getString("TempValue"), "C:/bigdata/base64/" + UUID.randomUUID() + ".jpg");
        }
    }
}
