package cn.itcast.haoke.dubbo.api.DataFetcher;

import cn.itcast.haoke.dubbo.api.service.HouseResourcesService;
import graphql.schema.DataFetchingEnvironment;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Component;

@Component
public class HouseResourcesDataFetcher implements MyDataFetcher {

    @Reference
    private HouseResourcesService houseResourcesService;
    @Override
    public String fileName() {
        return "HouseResources";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = dataFetchingEnvironment.getArgument("id");
        return houseResourcesService.queryHouseResourcesById(id);
    }
}
