package com.epipe.boot.service;

import com.epipe.boot.model.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


/**
 * kafka 生产者 将消息发送至指定topic中
 */
@Component
@Slf4j
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    /**
     * 向指定topic 发送信息
     */
    public void send() {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        log.info("+++++++++KafkaProducer+++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send("demo", gson.toJson(message));
    }

    /**
     * 向指定topic 发送自定义信息
     */
    public void send(final String msg) {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        message.setMsg(msg);
        message.setSendTime(new Date());
        log.info("+++++++++KafkaProducer+++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send("demo", gson.toJson(message));
    }
}
