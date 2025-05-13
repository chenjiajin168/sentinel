package com.chenjiajin.controller;

import com.chenjiajin.domain.Logtest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class DaughterController {

    @RequestMapping("/aop")
    public String aop(String test) {
        // http://localhost:80/aop
        System.out.println("这是执行方法");
        return "success";
    }


    @RequestMapping("/aop2")
    public String aop2() {
        // http://localhost:80/aop2
        System.out.println("这是执行方法2");
        return "success2";
    }


    @RequestMapping("/feign1")
    public Logtest feign1(@RequestBody Logtest logtest) {
        // http://localhost:80/feign1
        System.err.println(logtest);

        return new Logtest("55555", "66666", "7777", "8888");
    }

    @RequestMapping("/feign2")
    public Logtest feign2(@RequestBody Map<String, Logtest> map) {
        // http://localhost:80/feign2
        System.err.println(map);
        Logtest logtest = map.get("1");
        System.err.println(logtest);
        return new Logtest("55555", "66666", "7777", "8888");
    }

    @RequestMapping("/feign3")
    public Logtest feign3(@RequestBody Map<String, String> map) {
        // http://localhost:80/feign3
        System.err.println(map);
        String logtest = map.get("1");
        System.err.println(logtest);
        return new Logtest("55555", "66666", "7777", "8888");
    }


    @RequestMapping("/feign4")
    public Map<String, String> feign4(@RequestBody Logtest logtest) {
        // http://localhost:80/feign
        System.err.println(logtest);

        Map<String, String> map = new HashMap<>();
        map.put("1", "111");
        map.put("2", "222");
        map.put("3", "333");
        map.put("4", "444");
        return map;

    }

    @RequestMapping("/feign5")
    public Map<String, Logtest> feign5(@RequestBody Logtest logtest) {
        // http://localhost:80/feign
        System.err.println(logtest);

        Map<String, Logtest> map = new HashMap<>();
        map.put("1", new Logtest("1111", "1111", "1111", "1111"));
        map.put("2", new Logtest("2222", "2222", "2222", "2222"));
        map.put("3", new Logtest("3333", "3333", "3333", "3333"));
        map.put("4", new Logtest("4444", "4444", "4444", "4444"));
        return map;

    }

    @RequestMapping("/feign6")
    public List<String> feign6(@RequestBody Logtest logtest) {
        System.err.println(logtest);

        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        return list;

    }

    @RequestMapping("/feign7")
    public List<Logtest> feign7(@RequestBody Logtest logtest) {
        System.err.println(logtest);

        List<Logtest> list = new ArrayList<>();
        list.add(new Logtest("1111", "1111", "1111", "1111"));
        list.add(new Logtest("2222", "2222", "2222", "2222"));
        list.add(new Logtest("3333", "3333", "3333", "3333"));
        return list;

    }


    @RequestMapping("/feign_sentinel")
    public Logtest feign_sentinel(@RequestBody Logtest logtest) {
        Random random = new Random();
        int i = random.nextInt(100);
        System.err.println("i = " + i);
        if (i % 2 == 0) {
            int a = 1 / 0;
        }
        return new Logtest("55555", "66666", "7777", "8888");
    }


}