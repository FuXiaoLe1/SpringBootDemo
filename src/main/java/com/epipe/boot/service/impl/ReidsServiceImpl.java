package com.epipe.boot.service.impl;

import com.epipe.boot.service.ReidsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Description; redis实现类
 * redis 5种存储类型:
 * template.opsForValue();//操作字符串
 * template.opsForHash();//操作hash
 * template.opsForList();//操作list
 * template.opsForSet();//操作set
 * template.opsForZSet();//操作有序set
 */
@Service
public class ReidsServiceImpl implements ReidsService {

    @Autowired
    private StringRedisTemplate template;

    /**
     * 通过key-value 对redis赋值
     * times 为时间
     * redis缓存颗粒度:
     * 	 TimeUnit.DAYS          //天
     * 	 TimeUnit.HOURS         //小时
     * 	 TimeUnit.MINUTES       //分钟
     * 	 TimeUnit.SECONDS       //秒
     * 	 TimeUnit.MILLISECONDS  //毫秒
     * @param key
     * @param value
     * @param times
     */
    @Override
    public void set(String key, String value, long times) {
        ValueOperations<String, String> operations = template.opsForValue();
        operations.set(key,value,times,TimeUnit.SECONDS);
    }

    /**
     *
     *  通过key-value 对redis赋值
     * @param key
     * @param valuse
     */
    @Override
    public void set(String key, String valuse) {
        ValueOperations<String, String> operations = template.opsForValue();
        operations.set(key,valuse);
    }

    /**
     * 通过key获取value
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        ValueOperations<String, String> operations = template.opsForValue();
        String s = operations.get(key);
        return s;
    }
}
