package com.chenjiajin.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.chenjiajin.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @SentinelResource(value = "tranceService")
    public void tranceService() {
        log.info("调用tranceService方法");
    }


}


