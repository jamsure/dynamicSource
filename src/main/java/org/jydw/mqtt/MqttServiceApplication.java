package org.jydw.mqtt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class})
@MapperScan("org.jydw.mqtt.mapper")
@ServletComponentScan
@EnableEurekaClient
public class MqttServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqttServiceApplication.class, args);
    }

}
