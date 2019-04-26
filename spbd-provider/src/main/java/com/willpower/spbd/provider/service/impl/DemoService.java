package com.willpower.spbd.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.willpower.spbd.api.service.DemoService;
import org.springframework.stereotype.Component;

/**
 * Created by Powersoft on 2019/4/25.
 */
@Component
@Service
class DemoServiceImpl implements DemoService {
    @Override
    public String sayHi(String name) {
        return "<h1>I am " + name + "! I am from sprintboot + dubbo!</h1>";
    }
}
