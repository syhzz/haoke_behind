package cn.itcast.haoke.dubbo.api.controller;

import cn.itcast.haoke.dubbo.api.pojo.WebResult;
import cn.itcast.haoke.dubbo.api.service.AdService;
import cn.itcast.haoke.dubbo.pojo.PageInfo;
import cn.itcast.haoke.dubbo.server.pojo.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("ad")
@RestController
@CrossOrigin
public class AdController {
    @Autowired
    private AdService adService;

    @GetMapping
    public WebResult queryIndexAd() {
        PageInfo<Ad> adPageInfo = this.adService.queryAdList(1, 1, 3);
        List<Ad> records = adPageInfo.getRecords();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Ad record : records) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("original", record.getUrl());
            list.add(map);
        }
        return WebResult.ok(list);
    }
}
