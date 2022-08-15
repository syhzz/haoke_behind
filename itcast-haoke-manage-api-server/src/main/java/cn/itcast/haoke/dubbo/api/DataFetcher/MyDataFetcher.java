package cn.itcast.haoke.dubbo.api.DataFetcher;

import graphql.schema.DataFetchingEnvironment;

public interface MyDataFetcher {

    String fileName();

    Object dataFetcher(DataFetchingEnvironment dataFetchingEnvironment);
}
