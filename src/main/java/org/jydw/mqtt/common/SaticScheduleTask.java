package org.jydw.mqtt.common;

import org.jydw.mqtt.entity.TbCamera;
import org.jydw.mqtt.service.TbCameraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName SaticScheduleTask
 * @Description TODO
 * @Date 2019/3/27 10:14
 * @Author fankai
 * @Version 1.0
 **/
//@Component
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    Logger log = LoggerFactory.getLogger(SaticScheduleTask.class);

    @Autowired
    private TbCameraService tbCameraService;

    //3.添加定时任务
//    @Scheduled(cron = "${my.task.yx-status.cron}")
    //或直接指定时间间隔，例如：5秒
    @Scheduled(fixedRate = 5000)
    private void configureTasks() {

        System.out.println("merge 更新记录数 ：" + tbCameraService.getList(new TbCamera()));
//        DatabaseContextHolder.setDatabaseType(DatabaseType.v8DS);

    }
}
