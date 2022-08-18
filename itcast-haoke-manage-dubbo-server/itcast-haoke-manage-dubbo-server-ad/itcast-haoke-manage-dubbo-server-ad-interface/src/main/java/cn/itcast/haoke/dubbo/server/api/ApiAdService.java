package cn.itcast.haoke.dubbo.server.api;

import cn.itcast.haoke.dubbo.pojo.PageInfo;
import cn.itcast.haoke.dubbo.server.pojo.Ad;

public interface ApiAdService {
    /**
     * 查询广告数据
     * @param type  广告类型
     * @param page  页数
     * @param pageSize  条数
     * @return
     */
    PageInfo<Ad> queryAdList(Integer type, Integer page,Integer pageSize);
}
