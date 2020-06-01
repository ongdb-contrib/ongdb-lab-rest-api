package data.lab.ongdb.controller;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONObject;
import data.lab.ongdb.etl.common.CRUD;
import data.lab.ongdb.model.AuthUser;
import data.lab.ongdb.result.Result;
import data.lab.ongdb.services.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.controller.Controller
 * @Description: TODO(此CONTROLLER都是只读请求 - 可使用HTTP和BOLT协议)
 * @date 2020/5/31 13:18
 */
@Controller
@RequestMapping("/read")
/**
 * 支持跨源请求
 * **/
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReadController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReadController.class);

    /**
     * 注册数据逻辑处理类
     **/
    @Autowired
    protected ServiceImpl dataService;

    /**
     * @param para
     * @return
     * @Description: TODO(事务自动提交)
     */
    @RequestMapping(value = "/d/transaction/commit", method = RequestMethod.POST)
    @ResponseBody
    public Result dTransactionCommit(@RequestBody JSONObject para) {
        Result result;
        try {
            JSONObject statement = para.getJSONArray("statements").getJSONObject(0);
            String cypher = statement.getString("statement");
            String returnType = para.getString("return");
            String user = para.getString("user");
            String password = para.getString("password");
            if (para.containsKey("return") && CRUD.RETRIEVE_PROPERTIES.getSymbolValue().equals(returnType)) {
                return dataService.readAutoCommitCypher(new AuthUser(user, password), cypher, CRUD.RETRIEVE_PROPERTIES_READ_ONLY);
            } else {
                return dataService.readAutoCommitCypher(new AuthUser(user, password), cypher, CRUD.RETRIEVE_READ_ONLY);
            }
        } catch (IllegalArgumentException e) {
            result = new Result(new String[]{e.getMessage()}, e.hashCode());
        }
        return result;
    }

    @RequestMapping(value = "/h/task/query",method = RequestMethod.GET)
    @ResponseBody
    public Result taskQuery(AuthUser authUser) {
        return dataService.readTaskQuery(authUser);
    }

}





