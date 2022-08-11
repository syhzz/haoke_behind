package cn.itcast.haoke.dubbo.api.service;

import cn.itcast.haoke.dubbo.server.api.ApiHouseResourceService;
import cn.itcast.haoke.dubbo.server.pojo.HouseResources;
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
}
