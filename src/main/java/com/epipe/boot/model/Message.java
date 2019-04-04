package com.epipe.boot.model;

import lombok.Data;

import java.util.Date;

/**
 * kafka消息封装model
 *
 */
@Data  //简化代码编写 加载setter getter方法
public class Message {

    private Long id;
    /**
     * 消息
    * */
    private String msg;
    /**
     * 时间戳
     * */
    private Date sendTime;

}