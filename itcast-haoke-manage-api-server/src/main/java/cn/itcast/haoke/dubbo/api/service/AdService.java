package cn.itcast.haoke.dubbo.api.service;

import cn.itcast.haoke.dubbo.pojo.PageInfo;
import cn.itcast.haoke.dubbo.server.api.ApiAdService;
import cn.itcast.haoke.dubbo.server.pojo.Ad;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class AdService {

    @Reference(version = "1.0.0")
    private ApiAdService apiAdService;

    public PageInfo<Ad> queryAdList(int i, int i1, int i2) {
        PageInfo<Ad> pageInfo = this.apiAdService.queryAdList(i, i1, i2);
        return pageInfo;
    }
}
