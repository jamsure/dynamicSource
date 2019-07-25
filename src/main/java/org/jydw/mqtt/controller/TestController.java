package org.jydw.mqtt.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jydw.mqtt.entity.TbCamera;
import org.jydw.mqtt.interfaces.MqttGateway;
import org.jydw.mqtt.mapper.MqttMapper;
import org.jydw.mqtt.service.MqttService;
import org.jydw.mqtt.service.TbCameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TestController
 * @Description TODO
 * @Date 2019/4/3 15:02
 * @Author fankai
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MqttGateway mqttGateway;

    @Autowired
    private TbCameraService tbCameraService;
    @Autowired
    private MqttService mqttService;

    @RequestMapping("/sendMqttAndTopic")
    public String sendMqtt(String  sendData,String topic){
        mqttGateway.sendToMqtt(topic,0,true,sendData);
        return "OK";
    }

    @RequestMapping("/getTbCamera")
    public Object getTbCamera(String keyId){
        TbCamera tbCamera = new TbCamera(keyId);
        return tbCameraService.get(tbCamera);
    }

    @RequestMapping("/getRules")
    public Object getRules(String deviceId,String presetNo){
        Map map = new HashMap();
        map.put("deviceId",deviceId);
        map.put("presetNo",presetNo);
        return  mqttService.getRulesByDeviceIdAndPresetNo(map);
    }


    @RequestMapping("/testRules")
    public Object testRules(){
        String[] topic = { "jysp/iot/ir/devices/deviceId1002/notify",
                "jysp/iot/ir/devices/deviceId1003/notify",
                "jysp/iot/ir/devices/deviceId1005/notify"};

        String sendData = "{\"ProtocolType\":3,\"Preset\":1,\"TempValue\":\"xxxxxxxxxx\",\"TempArray\":[{\"Num\":1,\"Type\":\"Line\",\"Temp\":{\"Max\":40.0,\"Min\":20.1,\"Avg\":30.2},\"AlarmStu\":0},{\"Num\":1,\"Type\":\"Rect\",\"Temp\":{\"Max\":40.0,\"Min\":20.1,\"Avg\":30.2},\"AlarmStu\":0},{\"Num\":2,\"Type\":\"Rect\",\"Temp\":{\"Max\":40.0,\"Min\":20.1,\"Avg\":30.2},\"AlarmStu\":0},{\"Num\":3,\"Type\":\"Rect\",\"Temp\":{\"Max\":40.0,\"Min\":20.1,\"Avg\":30.2},\"AlarmStu\":0},{\"Num\":4,\"Type\":\"Rect\",\"Temp\":{\"Max\":40.0,\"Min\":20.1,\"Avg\":30.2},\"AlarmStu\":0}]}";
        for (int i = 0;i<100;i++){
            mqttGateway.sendToMqtt(topic[i%3],sendData);
            System.out.println("已发送："+i);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
        return "OK";
    }

}
