package com.chenjiajin.feign;


import com.chenjiajin.domain.Logtest;
import com.chenjiajin.feign.fallBack.FeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author chenjiajin
 * @date 2022/4/11
 */
@FeignClient(name = "daughter", fallback = FeignFallBack.class)
public interface FeignService {

    /**
     * 参数是JavaBean
     * 返回值是JavaBean
     */
    @RequestMapping("/feign1")
    Logtest feign1(@RequestBody Logtest logtest);  //对象和map需要贴RequestBody注解


    /**
     * 参数是Map<String, JavaBean>
     * 返回值是JavaBean
     */
    @RequestMapping("/feign2")
    Logtest feign2(@RequestBody Map<String, Logtest> map);


    /**
     * 参数是Map<String,String>
     * 返回值是JavaBean
     */
    @RequestMapping("/feign3")
    Logtest feign3(@RequestBody Map<String, String> map);
    // Map<String, String>  可以使用 @RequestParam  , 也可以使用 @RequestBody
    // Map<String, Logtest> 不能使用 @RequestParam  , 只能使用 @RequestBody


    /**
     * 参数是JavaBean
     * 返回值是Map<String,String>
     */
    @RequestMapping("/feign4")
    Map<String, String> feign4(@RequestBody Logtest logtest);


    /**
     * 参数是JavaBean
     * 返回值是Map<String,JavaBean>
     */
    @RequestMapping("/feign5")
    Map<String, Logtest> feign5(@RequestBody Logtest logtest);


    /**
     * 参数是JavaBean
     * 返回值是List<String>
     */
    @RequestMapping("/feign6")
    List<String> feign6(Logtest logtest);


    /**
     * 参数是JavaBean
     * 返回值是List<JavaBean>
     */
    @RequestMapping("/feign7")
    List<Logtest> feign7(Logtest logtest);


    /**
     * 参数是JavaBean
     * 返回值是JavaBean
     */
    @RequestMapping("/feign_sentinel")
    Logtest feign_sentinel(@RequestBody Logtest logtest);  //对象和map需要贴RequestBody注解

}
