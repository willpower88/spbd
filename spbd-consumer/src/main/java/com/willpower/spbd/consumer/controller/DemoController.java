package com.willpower.spbd.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.willpower.spbd.api.service.DemoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Powersoft on 2019/4/25.
 */
@RestController
public class DemoController {
    @Reference
    private DemoService demoService;

    @RequestMapping("/")
    public String index() {
        return demoService.sayHi("sam");
    }
}
