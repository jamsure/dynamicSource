package org.jydw.mqtt.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jydw.mqtt.common.utils.Base64Utils;
import org.jydw.mqtt.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @ClassName ReceiveHandler
 * @Description TODO
 * @Date 2019/4/3 15:12
 * @Author fankai
 * @Version 1.0
 * jysp/iot/ir/devices                          4
 * jysp/iot/ir/devices/{device_id}             5
 * jysp/iot/ir/devices/{device_id}/notify      6
 * jysp/iot/ir/devices/{device_id}/control     6
 * jysp/iot/ir/devices/public                  5
 **/
@Component
public class ReceiveHandler implements MessageHandler {

    @Autowired
    private ProtocolType3Handler protocolType3Handler;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        Long startTime = System.currentTimeMillis();
        System.out.println("处理开始：*********************");
        String topic = Objects.requireNonNull(message.getHeaders().get("mqtt_receivedTopic")).toString();
        String[] strs = topic.split("/");
        String deviceId = "";
        switch (strs.length){
            //jysp/iot/ir/devices 通用
            case 4:
                System.out.println("通用主题："+topic);
                break;
            case 5:
                if ("public".equals(strs[4])) {
                    System.out.println("通用public主题："+topic);
                } else {
                    System.out.println("device_id："+strs[4]+"，主题："+topic);
                    deviceId = strs[4];
                }
                break;
            case 6:
                if ("notify".equals(strs[5])) {
                    System.out.println("notify_deviceId："+strs[4]+"，主题："+topic);
                } else {
                    System.out.println("control_deviceId："+strs[4]+"，主题："+topic);
                }
                deviceId = strs[4];
                break;
            default:
                System.out.println("主题: "+topic);
                break;
        }
        String content = message.getPayload().toString();
        JSONObject object = JSONObject.parseObject(content);

        switch (object.getInteger("ProtocolType")){
            case 1:
                System.out.println("111");
                break;
            case 2:
                System.out.println("222");
                break;
            case 3:
                //{"ProtocolType":3,"Preset":0,"TempValue":"xxxxxxxxxx","TempArray":[{"Num":1,"Type":"Line","Temp":{"Max":40.0,"Min":20.1,"Avg":30.2},"AlarmStu":0},{"Num":2,"Type":"Rect","Temp":{"Max":40.0,"Min":20.1,"Avg":30.2},"AlarmStu":0}]}
                Map<String,Object> map = new HashMap<>();
                map.put("deviceId",deviceId);
                map.put("jsonObject",object);
                protocolType3Handler.handler(map);

                break;

            case 4:
                System.out.println("222");
                break;
            case 5:
                Long startTime5 = System.currentTimeMillis();
                System.out.println("保存图片处理开始：*********************");
                TypeHandler.base64Handler(object);
                Long endTime5 = System.currentTimeMillis();
                System.out.println("保存图片处理结束：*********************用时:"+(endTime5-startTime5)+"ms");
                break;
            case 6:
                System.out.println("222");
                break;
            case 7:
                System.out.println("222");
                break;
            case 8:
                System.out.println("222");
                break;
            case 9:
                System.out.println("222");
                break;
            case 10:
                System.out.println("222");
                break;
                default:
                    System.out.println("default");

        }

        Long endTime = System.currentTimeMillis();
        System.out.println("处理结束：*********************用时:"+(endTime-startTime)+"ms");
    }


}
