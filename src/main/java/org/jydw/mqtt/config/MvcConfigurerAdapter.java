package org.jydw.mqtt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName MvcConfigurerAdapter
 * @Description TODO
 * @Date 2019/6/13 11:41
 * @Author fankai
 * @Version 1.0
 **/
public class MvcConfigurerAdapter {

    @Configuration
    public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
        /**
         * 配置静态访问资源
         * @param registry
         */
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            super.addResourceHandlers(registry);
        }
    }
}
