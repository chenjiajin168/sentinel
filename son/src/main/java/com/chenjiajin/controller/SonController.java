package com.chenjiajin.controller;

import com.chenjiajin.domain.Logtest;
import com.chenjiajin.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SonController {

    @Autowired
    private FeignService feignService;

    /**
     * 测试接口
     */
    @RequestMapping("/demo")
    public String aop(String test) {      // http://localhost:81/aop
        System.out.println("这是demo方法");
        return "demo";
    }

    /**
     * 参数是JavaBean
     * 返回值是JavaBean
     */
    @RequestMapping("/feign1")
    public Logtest feign1() {
        // http://localhost:81/feign1
        Logtest logtest = feignService.feign1(new Logtest("1111", "2222", "3333", "4444"));
        return logtest;
    }

    /**
     * 参数是Map<String, JavaBean>
     * 返回值是JavaBean
     */
    @RequestMapping("/feign2")
    public Logtest feign2() {
        Map<String, Logtest> map = new HashMap<>();
        map.put("1", new Logtest("1111", "1111", "1111", "1111"));
        map.put("2", new Logtest("2222", "2222", "2222", "2222"));
        map.put("3", new Logtest("3333", "3333", "3333", "3333"));
        map.put("4", new Logtest("4444", "4444", "4444", "4444"));
        Logtest logtest = feignService.feign2(map);
        return logtest;
    }

    /**
     * 参数是Map<String,String>
     * 返回值是JavaBean
     */
    @RequestMapping("/feign3")
    public Logtest feign3() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1111");
        map.put("2", "2222");
        map.put("3", "3333");
        map.put("4", "4444");
        Logtest logtest = feignService.feign3(map);
        return logtest;
    }


    /**
     * 参数是JavaBean
     * 返回值是Map<String,String>
     */
    @RequestMapping("/feign4")
    public Map<String, String> feign4() {
        Logtest logtest = new Logtest("1111", "2222", "3333", "4444");
        Map<String, String> map = feignService.feign4(logtest);
        return map;
    }




    /**
     * 参数是JavaBean
     * 返回值是Map<String,JavaBean>
     */
    @RequestMapping("/feign5")
    public Map<String, Logtest> feign5() {
        Logtest logtest = new Logtest("1111", "2222", "3333", "4444");
        Map<String, Logtest> map = feignService.feign5(logtest);
        Logtest logtest1 = map.get("1");
        System.err.println(logtest1);
        return map;
    }



    /**
     * 参数是JavaBean
     * 返回值是List<String>
     */
    @RequestMapping("/feign6")
    public List<String> feign6() {
        Logtest logtest = new Logtest("1111", "2222", "3333", "4444");
        List<String> map = feignService.feign6(logtest);
        return map;
    }



    /**
     * 参数是JavaBean
     * 返回值是List<JavaBean>
     */
    @RequestMapping("/feign7")
    public List<Logtest> feign7() {
        Logtest logtest = new Logtest("1111", "2222", "3333", "4444");
        List<Logtest> list = feignService.feign7(logtest);
        Logtest logtest1 = list.get(0);
        System.err.println(logtest1);
        return list;
    }






}