package org.jydw.mqtt.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @ClassName ProtocolHandler
 * @Description TODO
 * @Date 2019/7/25 9:49
 * @Author fankai
 * @Version 1.0
 **/
public interface ProtocolHandler {

    void handler(Map<String,Object> params);
}
