package org.jydw.mqtt.interfaces;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @ClassName MqttGateway
 * @Description TODO
 * @Date 2019/4/3 15:01
 * @Author fankai
 * @Version 1.0
 **/
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    /**
     * @Description 发布消息，默认主题
     * @Date  2019/4/3 16:23
     * @Author fankai
     * @Param [data 消息内容]
     * @return void
     **/
    void sendToMqtt(String data);

    /**
     * @Description 发布消息，指定主题
     * @Date  2019/4/3 16:24
     * @Author fankai
     * @Param [topic 主题, payload 消息内容]
     * @return void
     **/
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    /**
     * @Description 发布消息，指定主题,qos
     * @Date  2019/4/3 16:24
     * @Author fankai
     * @Param [topic 主题, qos 消息类型(0,1,2), payload 消息内容]
     * @return void
     **/
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

    /**
     * @Description 发布消息，指定主题,qos,是否保留消息
     * Broker会存储每个Topic的最后一条保留消息及其Qos，当订阅该Topic的客户端上线后，Broker需要将该消息投递给它。
     * 删除方式 方式1：发送空消息体的保留消息；
     *          方式2：发送最新的保留消息覆盖之前的（推荐）
     * @Date  2019/4/3 16:25
     * @Author fankai
     * @Param [topic 主题, qos 消息类型(0,1,2), retained 是否保留消息, payload]
     * @return void
     **/
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, @Header(MqttHeaders.RETAINED)boolean retained, String payload);
}
