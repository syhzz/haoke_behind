package cn.itcast.haoke.dubbo.api.config;

import cn.itcast.haoke.dubbo.api.DataFetcher.MyDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Component
public class GraphqlProvider {
    private GraphQL graphQL;


    @Autowired
    private List<MyDataFetcher> myDataFetchers;

    @PostConstruct
    public void init() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:haoke.graphqls");
        GraphQLSchema graphQLSchema = buildSchema(file);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(File file) {
        TypeDefinitionRegistry parse = new SchemaParser().parse(file);
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        RuntimeWiring runtimewiring = buildWiring();
        return schemaGenerator.makeExecutableSchema(parse, runtimewiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("HaokeQuery", builder -> {
                            for (MyDataFetcher myDataFetcher : myDataFetchers) {
                                builder.dataFetcher(myDataFetcher.fileName(), myDataFetcher::dataFetcher);
                            }
                            return builder;
                        }
//                        builder.dataFetcher(
//                        "HouseResources", environment -> {
//                            Long id = environment.getArgument("id");
//                            return this.houseResourcesService.queryHouseResourcesById(id);
//                        }
//                )
                )
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }
}
