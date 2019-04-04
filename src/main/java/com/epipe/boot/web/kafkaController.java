package com.epipe.boot.web;

import com.epipe.boot.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送信息至指定kafka消息队列
 * 调用方式：http://localhost:8080/send?msg=XXXX
 */
@RestController
@Slf4j
public class kafkaController {
    @Autowired
    KafkaProducerService kafkaProducerService;
    @GetMapping(value = "/send")
    public String send(String msg){
        try {
            kafkaProducerService.send(msg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "failure";
        }
        return "success";
    }

}
