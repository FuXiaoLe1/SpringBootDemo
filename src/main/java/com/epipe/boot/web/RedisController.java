package com.epipe.boot.web;

import com.epipe.boot.service.ReidsService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 运用redis创建一个小Demo
 * Created by fxl
 */
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {
    @Autowired
    ReidsService reidsService;

    @ApiOperation(value="保存至redis", notes="保存至redis")
    @RequestMapping(value="/set", method=RequestMethod.GET)
    public String set(@RequestParam String key,@RequestParam String value) {
        reidsService.set(key,value);
        log.info("已经存入redis======key:"+key+"value:"+value);
        return "已经存入redis!";
    }

    @ApiOperation(value="根据key获取value", notes="根据key获取value")
    @RequestMapping(value="/getkey", method=RequestMethod.GET)
    public String getkey(@RequestParam String key) {
        String value=reidsService.get(key);
        log.info("["+key+"]======值为:"+value);
        return "["+key+"]=======值为:"+value;
    }

}
