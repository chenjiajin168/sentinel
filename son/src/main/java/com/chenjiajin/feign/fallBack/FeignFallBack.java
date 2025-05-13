package com.chenjiajin.feign.fallBack;

import com.chenjiajin.domain.Logtest;
import com.chenjiajin.feign.FeignService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class FeignFallBack implements FeignService {

    @Override
    public Logtest feign_sentinel(Logtest logtest) {
        // feign对Sentinel的支持
        return new Logtest("兜底数据", "兜底数据", "兜底数据", "兜底数据");
    }

    @Override
    public Logtest feign1(Logtest logtest) {
        return null;
    }

    @Override
    public Logtest feign2(Map<String, Logtest> map) {
        return null;
    }

    @Override
    public Logtest feign3(Map<String, String> map) {
        return null;
    }

    @Override
    public Map<String, String> feign4(Logtest logtest) {
        return null;
    }

    @Override
    public Map<String, Logtest> feign5(Logtest logtest) {
        return null;
    }

    @Override
    public List<String> feign6(Logtest logtest) {
        return null;
    }

    @Override
    public List<Logtest> feign7(Logtest logtest) {
        return null;
    }
}