package cn.itcast.haoke.dubbo.server.service;

import cn.itcast.haoke.dubbo.pojo.PageInfo;
import cn.itcast.haoke.dubbo.server.pojo.Ad;

public interface AdService {
    PageInfo<Ad> queryAdList(Ad ad, Integer page, Integer pageSize);
}
