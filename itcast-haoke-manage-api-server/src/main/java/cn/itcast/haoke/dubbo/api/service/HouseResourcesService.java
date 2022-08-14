package cn.itcast.haoke.dubbo.api.service;

import cn.itcast.haoke.dubbo.api.pojo.Pagination;
import cn.itcast.haoke.dubbo.api.pojo.TableResult;
import cn.itcast.haoke.dubbo.server.api.ApiHouseResourceService;
import cn.itcast.haoke.dubbo.server.pojo.HouseResources;
import cn.itcast.haoke.dubbo.server.pojo.PageInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class HouseResourcesService {

    @Reference(version = "1.0.0")
    private ApiHouseResourceService apiHouseResourceService;

    public boolean save(HouseResources houseResources) {
        int i = this.apiHouseResourceService.saveHouseResources(houseResources);
        return i == 1;
    }

    public Object queryList(HouseResources houseResources, Integer currentPage, Integer pageSize) {
        PageInfo<HouseResources> pageInfo = this.apiHouseResourceService.queryHouseReourcesList(currentPage, pageSize, houseResources);
        return new TableResult(pageInfo.getRecords(), new Pagination(currentPage, pageSize, pageInfo.getTotal()));
    }

}
