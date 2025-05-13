package com.chenjiajin.utils;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权规则
 */
@Component
public class RequestOriginParserDefinition implements RequestOriginParser {

    /**
     * 这个方法基于业务规则对请求数据进行解析
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        /**
         *  定义从请求的什么地方获取来源信息
         *  比如我们可以要求所有的客户端需要在请求头中携带来源信息
         */
        // 1.解析请求参数,并返回参数值,然后将这个值应用在Sentinel的授权规则上
        String serviceName = request.getParameter("serviceName");
        return serviceName;
    }

}