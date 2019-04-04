package com.epipe.boot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fastjson配置，
 *
 * 把spring-boot默认的json解析器由Jenkins换为fastjson
 * Created by lujun.chen on 2017/4/18.
 */
@Configuration
public class FastjsonConfiguration {


  @Bean
  public HttpMessageConverters fastjsonConverter() {
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    //自定义格式化输出
    fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero);

    FastJsonHttpMessageConverter4 fastjson = new FastJsonHttpMessageConverter4();
    fastjson.setFastJsonConfig(fastJsonConfig);
    return new HttpMessageConverters(fastjson);
  }

}
