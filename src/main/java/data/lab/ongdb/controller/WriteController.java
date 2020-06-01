package data.lab.ongdb.controller;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONArray;
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

import java.util.LinkedHashMap;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.controller.Controller
 * @Description: TODO(包含数据的更新导入等等操作的接口在此CONTROLLER - 必须使用DRIVER中BOLT协议实现)
 * @date 2020/5/31 13:18
 */
@Controller
@RequestMapping("/write")
@CrossOrigin(origins = "*", maxAge = 3600) // 为了支持跨源请求添加注解
public class WriteController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReadController.class);

    /**
     * 注册数据逻辑处理类
     **/
    @Autowired
    protected ServiceImpl dataService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "Hello world!";
    }

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
                return dataService.readAutoCommitCypher(new AuthUser(user, password), cypher, CRUD.RETRIEVE_PROPERTIES);
            } else {
                return dataService.readAutoCommitCypher(new AuthUser(user, password), cypher, CRUD.RETRIEVE);
            }
        } catch (IllegalArgumentException e) {
            result = new Result(new String[]{e.getMessage()}, e.hashCode());
        }
        return result;
    }

    /**
     * @param para
     * @return
     * @Description: TODO(提交后台任务并返回任务ID)
     */
    @RequestMapping(value = "/d/transaction/commit/task", method = RequestMethod.POST)
    @ResponseBody
    public Result dTransactionCommitTask(@RequestBody JSONObject para) {
        Result result = new Result(200);
        try {
            JSONArray array = para.getJSONArray("statements");
            String user = para.getString("user");
            String password = para.getString("password");
            for (Object object : array) {
                LinkedHashMap statement = (LinkedHashMap) object;
                String cypher = String.valueOf(statement.get("statement"));
                String taskId = String.valueOf(statement.get("task-id"));
                result = dataService.writeAutoCommitCypherTask(new AuthUser(user, password), cypher, taskId);
            }
        } catch (IllegalArgumentException e) {
            result = new Result(new String[]{e.getMessage()}, e.hashCode());
        }
        return result;
    }

}





