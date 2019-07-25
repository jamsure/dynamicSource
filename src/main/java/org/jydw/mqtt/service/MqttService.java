package org.jydw.mqtt.service;

import org.jydw.mqtt.entity.TbCamera;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TbCameraService
 * @Description TODO
 * @Date 2019/6/3 16:18
 * @Author fankai
 * @Version 1.0
 **/
public interface MqttService {

     List<Map<String,String>> getRulesByDeviceIdAndPresetNo(Map<String,String> map);
}
