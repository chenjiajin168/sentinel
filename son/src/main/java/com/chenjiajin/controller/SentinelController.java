package com.chenjiajin.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.chenjiajin.domain.Logtest;
import com.chenjiajin.feign.FeignService;
import com.chenjiajin.service.impl.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class SentinelController {

    @Autowired
    private TestServiceImpl service;

//---------------------------限流规则------------------------------------

    // http://localhost:81/sentinel1
    @RequestMapping("/sentinel1")
    public String sentinel1() throws InterruptedException {
        System.err.println("sentinel1");
        //使用jmeter 50个线程访问该接口 , 耗尽tomcat的并发数 , 模拟一次网络延时
        TimeUnit.SECONDS.sleep(1);
        return "sentinel1";
    }

    // http://localhost:81/sentinel2
    @RequestMapping("/sentinel2")
    public String sentinel2() {
        // 使用jmeter的时候访问该接口 可以看到访问速度非常慢
        System.err.println("sentinel2");
        return "测试高并发下的问题";
    }

    @RequestMapping("/sentinel3")
    public String sentinel3() {
        System.err.println("sentinel3");
        return "sentinel3";
    }

//---------------------------链路流控------------------------------------

    // 模拟链路流控  需要web-context-unify: false配置
    @RequestMapping("/trance1")
    public String trance1() {
        System.err.println("trance1");
        service.tranceService();
        return "trance1";
    }

    @RequestMapping("/trance2")
    public String trance2() {
        System.err.println("trance2");
        service.tranceService();
        return "trance2";
    }

//---------------------------降级规则------------------------------------

    @RequestMapping("/fallBack1")  // 慢调用比例
    public String fallBack1() throws InterruptedException {
        log.info("fallBack1执行业务逻辑");
        //模拟业务耗时
        TimeUnit.SECONDS.sleep(1);
        return "fallBack1";
    }


    int i = 0;

    @RequestMapping("/fallBack2")  // 异常比例
    public String fallBack2() {
        log.info("fallBack2执行业务逻辑");
        //模拟出现异常，异常比例为33%
        if (++i % 3 == 0) {
            throw new RuntimeException();
        }
        return "fallBack2";
    }

    @RequestMapping("/fallBack3")
    public String fallBack3(String name) {  // 异常数
        log.info("fallBack3执行业务逻辑");
        if (name != null) {
            throw new RuntimeException();
        }
        return "fallBack3";
    }

//---------------------------热点规则------------------------------------

    @RequestMapping("/hotSpot1")
    @SentinelResource(value = "hotSpot1")
    public String hotSpot1(Long userId, Long productId) {  //  热点规则在productId
        log.info("访问编号为:{}的商品", productId);
        return "hotSpot1";
    }

//---------------------------授权规则------------------------------------


    @RequestMapping("/auth1")
    public String auth1(String serviceName) {
        log.info("应用:{},访问接口", serviceName);
        return "auth1";
    }

//---------------------------自定义限流和降级后的处理方法------------------------------------

    /**
     * 接口要求:
     * 当被限流或者降级的时候返回指定的字符
     * 当发生异常的时候返回指定的字符
     */
    @RequestMapping("/anno1")
    @SentinelResource(value = "anno1",              //指定的接口
            blockHandler = "anno1BlockHandler",     //指定限流之后的处理
            fallback = "anno1Fallback")             //指定异常之后的处理
    public String anno1(String name) {
        if (name == null) {
            throw new RuntimeException();
        }
        return "anno1";
    }

    public String anno1BlockHandler(String name, BlockException ex) { // 限流后的处理
        log.error("{}", ex);
        return "自定义限流和降级后的处理方法: 接口被 限流或者降级 !";
    }

    //Throwable时进入的方法
    public String anno1Fallback(String name, Throwable throwable) { // 出异常后的处理
        log.error("{}", throwable);
        return "自定义限流和降级后的处理方法: 接口发生 异常 !";
    }


//-------------------------------------feign对Sentinel的支持---------------------------------------------------

    @Autowired
    private FeignService feignService;

    /**
     * 参数是JavaBean
     * 返回值是JavaBean
     */
    @RequestMapping("/feign_sentinel")
    public Logtest feign1() {
        // http://localhost:81/feign1
        Logtest logtest = feignService.feign_sentinel(new Logtest("1111", "2222", "3333", "4444"));
        System.err.println("logtest = " + logtest);
        return logtest;
    }


}