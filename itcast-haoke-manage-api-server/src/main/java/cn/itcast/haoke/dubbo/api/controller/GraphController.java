package cn.itcast.haoke.dubbo.api.controller;

import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("graphql")
@Controller
public class GraphController {

    @Autowired
    private GraphQL graphQL;

    @GetMapping
    @ResponseBody
    public Map<String, Object> graphql(@RequestParam("query") String query) {
        return this.graphQL.execute(query).toSpecification();
    }
}
