package data.lab.ongdb.controller;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONObject;
import data.lab.ongdb.model.AuthUser;
import data.lab.ongdb.services.ServiceImpl;
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
    public String hTransactionCommit(@RequestBody String para) {
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

