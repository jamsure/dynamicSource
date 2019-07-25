package org.jydw.mqtt.mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TbCameraMapper
 * @Description TODO
 * @Date 2019/6/3 16:16
 * @Author fankai
 * @Version 1.0
 **/
public interface MqttMapper {

    /**
     * 根据deviceid 和 预置位编号 获取 报警规则
     * @param map rule id
     * @return Map<XxxxDO>
     */
    List<Map<String,String>> getRulesByDeviceIdAndPresetNo(Map<String,String> map);

}
