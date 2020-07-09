package data.lab.ongdb.controller;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONObject;
import data.lab.ongdb.model.AuthUser;
import data.lab.ongdb.services.ServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.controller
 * @Description: TODO(GraphQL端点)
 * @date 2020/7/8 21:20
 */
@Controller
@RequestMapping("/graph")
/**
 * 支持跨源请求
 * **/
@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "GraphQL",description = "执行GraphQL: /ongdb/graphiql在这个端点访问GraphQL APP")
public class GraphController {

    private final static Logger LOGGER = LoggerFactory.getLogger(GraphController.class);

    /**
     * 注册数据逻辑处理类
     **/
    @Autowired
    protected ServiceImpl dataService;

    /**
     * @param
     * @return
     * @Description: TODO(修改数据)
     * {
     * "query": "GraphQL",
     * "variables": {
     * "var1": "vakue",
     * "var2": "vakue"
     * }
     * }
     */
    @RequestMapping(value = "/graphql", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "执行数据修改的GraphQL",notes = "### POST /ongdb/graph/graphql/\n" +
            "- 参数样例\n" +
            "- GraphQL APP\n" +
            "```\n" +
            "graphql: \n" +
            "mutation {\n" +
            "  createColumn(name: \"The Shape of Water\")\n" +
            "}\n" +
            "query variables:\n" +
            "{\n" +
            "  \"username\":\"ongdb\",\n" +
            "  \"password\":\"ongdb%dev\"\n" +
            "}\n" +
            "```\n" +
            "- HTTP APP\n" +
            "```\n" +
            "{\n" +
            "  \"query\": \"mutation {\\n  createColumn(name: \\\"The Shape of Water\\\")\\n}\\n\",\n" +
            "  \"variables\": {\n" +
            "    \"username\": \"ongdb\",\n" +
            "    \"password\": \"ongdb%dev\"\n" +
            "  }\n" +
            "}\n" +
            "```\n" +
            "- 返回值\n" +
            "```\n" +
            "{\"data\":{\"createColumn\":\"Nodes created: 1\\nProperties set: 1\\nLabels added: 1\\n\"}}\n" +
            "```")
    public String executeGraphQl(@RequestBody String para) {
        AuthUser authUser = getAuthUser(para);
        return dataService.executeGraphQL(authUser, para);
    }

    /**
     * @param
     * @return
     * @Description: TODO(查数据)
     * {
     * "query": "GraphQL",
     * "variables": {
     * "var1": "vakue",
     * "var2": "vakue"
     * }
     * }
     */
    @RequestMapping(value = "/graphql/experimental", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "执行数据查询的GraphQL和定义Schema的GraphQL",notes = "- 参数样例\n" +
            "- GraphQL APP\n" +
            "```\n" +
            "graphql: \n" +
            "query {column(name:\"ods.test_table.c1\") {name}}\n" +
            "query variables:\n" +
            "{\n" +
            "  \"username\":\"ongdb\",\n" +
            "  \"password\":\"ongdb%dev\"\n" +
            "}\n" +
            "```\n" +
            "- HTTP APP\n" +
            "```\n" +
            "{\n" +
            "  \"query\": \"query {column(name:\\\"ods.test_table.c1\\\") {name}}\",\n" +
            "  \"variables\": {\n" +
            "    \"username\": \"ongdb\",\n" +
            "    \"password\": \"ongdb%dev\"\n" +
            "  }\n" +
            "}\n" +
            "```\n" +
            "- 返回值\n" +
            "```\n" +
            "{\"data\":[{\"column\":{\"name\":\"ods.test_table.c1\"}}]}\n" +
            "```")
    public String executeGraphQlEx(@RequestBody String para) {
        AuthUser authUser = getAuthUser(para);
        return dataService.executeGraphQLEx(authUser, para);
    }

    /**
     * @param
     * @return
     * @Description: TODO(封装校验的用户对象)
     */
    private AuthUser getAuthUser(String para) {
        JSONObject paraObj = JSONObject.parseObject(para);
        JSONObject authObj = paraObj.getJSONObject("variables");
        String username = authObj.getString("username");
        String password = authObj.getString("password");
        return new AuthUser(username, password);
    }
}

