package org.jydw.mqtt.mapper;

import org.jydw.mqtt.entity.TbCamera;

import java.util.List;

/**
 * @ClassName TbCameraMapper
 * @Description TODO
 * @Date 2019/6/3 16:16
 * @Author fankai
 * @Version 1.0
 **/
public interface TbCameraMapper {
    TbCamera get(TbCamera tbCamera);

    List<TbCamera> getList(TbCamera tbCamera);
}
