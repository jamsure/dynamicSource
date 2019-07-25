package org.jydw.mqtt.service;

import org.jydw.mqtt.entity.TbCamera;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName TbCameraService
 * @Description TODO
 * @Date 2019/6/3 16:18
 * @Author fankai
 * @Version 1.0
 **/
@Transactional(readOnly = true)
public interface TbCameraService {

    TbCamera get(TbCamera tbCamera);

    List<TbCamera> getList(TbCamera tbCamera);
}
