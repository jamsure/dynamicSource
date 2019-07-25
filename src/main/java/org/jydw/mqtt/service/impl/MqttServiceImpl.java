package org.jydw.mqtt.service.impl;

import org.jydw.mqtt.mapper.MqttMapper;
import org.jydw.mqtt.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TbCameraServiceImpl
 * @Description TODO
 * @Date 2019/6/3 16:19
 * @Author fankai
 * @Version 1.0
 **/
@Component
@Transactional(readOnly = true)
public class MqttServiceImpl implements MqttService {
    @Autowired
    private MqttMapper mqttMapper;

    @Override
    @Transactional(readOnly = false)
    public List<Map<String,String>> getRulesByDeviceIdAndPresetNo(Map<String, String> map){
        return mqttMapper.getRulesByDeviceIdAndPresetNo(map);
    };

}
