package cn.itcast.haoke.dubbo.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionInput;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("graphql")
@Controller
public class GraphController {

    @Autowired
    private GraphQL graphQL;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @GetMapping
    @ResponseBody
    public Map<String, Object> graphql(@RequestParam("query") String query) {
        return this.graphQL.execute(query).toSpecification();
    }

    @PostMapping
    @ResponseBody
    public Map<String, Object> postGraphql(@RequestBody Map<String, Object> param){
        try {
            String query = (String) param.get("query");
            if (query == null) {
                query = "";
            }
            Map variables = (Map) param.get("variables");
            if (variables == null) {
                variables = Collections.EMPTY_MAP;
            }
            String operationName = (String) param.get("operationName");
            ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query)
                    .operationName(operationName)
                    .variables(variables).build();
            return this.graphQL.execute(executionInput).toSpecification();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("msg", "查询出错");
        return map;
    }

    @GetMapping
    @ResponseBody
    public Map<String, Object> query(@RequestParam("query") String query,
                                     @RequestParam(value = "variables", required = false) String variablesJson,
                                     @RequestParam(value = "operationName", required = false) String operationName) {
        try {
            Map variables = MAPPER.readValue(variablesJson, MAPPER.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class));
            ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(query).operationName(operationName)
                    .variables(variables).build();
            return this.graphQL.execute(executionInput).toSpecification();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", 500);
        map.put("msg", "查询出错");
        return map;
    }
}
