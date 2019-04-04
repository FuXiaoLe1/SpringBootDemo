package com.epipe.boot.service;

/**
 * @Description;Reids接口，定义redis基本方法
 */
public interface ReidsService {

    void  set(String key, String value, long times);

    void  set(String key, String valuse);

    String get(String key);
}
