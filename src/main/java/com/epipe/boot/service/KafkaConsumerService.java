package com.epipe.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 消费者
 * 监听指定topic
 * 从指定topic获取消息
 */

@Component
@Slf4j
public class KafkaConsumerService {

    /**
     * 监听指定topic 未消费的资源
     * @param record
     */
    //@KafkaListener(topics = {"demo"})
    public void listen(ConsumerRecord<?, ?> record) {
        //Java 8 增加新特性 API 可以优雅的解决 NullPointException 的问题
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {//返回对象是否有值

            Object message = kafkaMessage.get();

            log.info("-------KafkaConsumer---------- record==>" + record);//队列相关信息
            log.info("-------KafkaConsumer----------message==>" + message);//消息
        }

    }
}
