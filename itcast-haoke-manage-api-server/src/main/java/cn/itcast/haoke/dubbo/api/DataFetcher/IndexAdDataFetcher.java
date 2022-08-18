package cn.itcast.haoke.dubbo.api.DataFetcher;

import cn.itcast.haoke.dubbo.api.pojo.IndexAdResult;
import cn.itcast.haoke.dubbo.api.pojo.IndexAdResultData;
import cn.itcast.haoke.dubbo.api.service.AdService;
import cn.itcast.haoke.dubbo.pojo.PageInfo;
import cn.itcast.haoke.dubbo.server.pojo.Ad;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IndexAdDataFetcher implements MyDataFetcher {
    @Autowired
    private AdService adService;

    @Override
    public String fileName() {
        return "IndexAdList";
    }

    @Override
    public Object dataFetcher(DataFetchingEnvironment dataFetchingEnvironment) {
        PageInfo<Ad> adPageInfo = this.adService.queryAdList(1, 1, 3);
        List<Ad> records = adPageInfo.getRecords();
        ArrayList<IndexAdResultData> list = new ArrayList<>();
        for (Ad record : records) {
            list.add(new IndexAdResultData(record.getUrl()));
        }
        return new IndexAdResult(list);
    }
}
