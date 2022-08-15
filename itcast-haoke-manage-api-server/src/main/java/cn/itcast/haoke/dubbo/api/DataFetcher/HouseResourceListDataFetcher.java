package cn.itcast.haoke.dubbo.api.DataFetcher;

import cn.itcast.haoke.dubbo.api.service.HouseResourcesService;
import cn.itcast.haoke.dubbo.server.pojo.HouseResources;
import graphql.schema.DataFetchingEnvironment;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Component;

@Component
public class HouseResourceListDataFetcher implements MyDataFetcher {
    @Reference
    private HouseResourcesService houseResourcesService;
    @Override
    public String fileName() {
        return "HouseResourcesList";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment dataFetchingEnvironment) {
        HouseResources houseResources = dataFetchingEnvironment.getArgument("houseResources");
        Integer page = dataFetchingEnvironment.getArgument("page");
        Integer pageSize = dataFetchingEnvironment.getArgument("pageSize");
        return houseResourcesService.queryList(houseResources, page == null ? 1 : page, pageSize == null ? 5 : pageSize);
    }
}
