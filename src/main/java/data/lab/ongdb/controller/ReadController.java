package data.lab.ongdb.controller;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONObject;
import data.lab.ongdb.etl.common.CRUD;
import data.lab.ongdb.model.ParaJson;
import data.lab.ongdb.result.Result;
import data.lab.ongdb.services.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.controller.Controller
 * @Description: TODO
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
            if (para.containsKey("return") && CRUD.RETRIEVE_PROPERTIES.getSymbolValue().equals(returnType)) {
                return dataService.readAutoCommitCypher(cypher, CRUD.RETRIEVE_PROPERTIES);
            } else {
                return dataService.readAutoCommitCypher(cypher, CRUD.RETRIEVE);
            }
        } catch (IllegalArgumentException e) {
            result = new Result(new String[]{e.getMessage()}, e.hashCode());
        }
        return result;
    }

    @RequestMapping(value = "/h/task/query", method = RequestMethod.GET)
    @ResponseBody
    public Result taskQuery() {
        return dataService.taskQuery();
    }

    /**
     * @param
     * @return
     * @Description: TODO(事务自动提交)
     */
    @RequestMapping(value = "/h/transaction/commit", method = RequestMethod.POST)
    @ResponseBody
    public String hTransactionCommit(@RequestBody ParaJson userJson) {
        return "";
    }

}





