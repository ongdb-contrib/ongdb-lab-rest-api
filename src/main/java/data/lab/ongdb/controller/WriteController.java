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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "RESTFUL-WRITE", value = "read", description = "执行数据修改CYPHER")
public class WriteController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ReadController.class);

    /**
     * 注册数据逻辑处理类
     **/
    @Autowired
    protected ServiceImpl dataService;


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "HELLO WORLD", notes = "- 参数样例 - 无传入参数\n" +
            "- 返回值样例\n" +
            "```\n" +
            "Hello world!\n" +
            "```")
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
    @ApiOperation(value = "执行数据修改查询", notes = "- 参数样例\n" +
            "```json\n" +
            "{\n" +
            "    \"statements\": [\n" +
            "        {\n" +
            "            \"statement\": \"CREATE CONSTRAINT ON (n:TASKRESULTNODE) ASSERT (n.taskId, n.SOURCE,n.TARGET,n.AMOUNT,n.DATE) IS NODE KEY\"\n" +
            "        }\n" +
            "    ],\n" +
            "     \"user\":\"neo4j\",\n" +
            "    \"password\":\"123456\"\n" +
            "}\n" +
            "```\n" +
            "- 返回值样例\n" +
            "```json\n" +
            "{\n" +
            "    \"error\": null,\n" +
            "    \"code\": 200,\n" +
            "    \"result\": {\n" +
            "        \"totalNodeSize\": 0,\n" +
            "        \"totalRelationSize\": 0,\n" +
            "        \"results\": [\n" +
            "            {\n" +
            "                \"data\": [\n" +
            "                    {\n" +
            "                        \"graph\": {\n" +
            "                            \"relationships\": [],\n" +
            "                            \"nodes\": [],\n" +
            "                            \"properties\": []\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"columns\": []\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}\n" +
            "```")
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
    @ApiOperation(value = "使用WRITE请求提交后台一个查询任务并将结果集写入ONgDB【此请求集群暂时无法分发只能在LEADER执行】", notes = ">【使用此接口获取任务结果/ongdb/read/d/transaction/commit MATCH (n:TASKRESULTNODE) WHERE n.taskId IN ['yc-m-task-1-关系网络任务','yc-m-task-2-关系网络任务'] RETURN n】\n" +
            ">任务ID的生成方式建议使用UUID\n" +
            "- 参数样例\n" +
            "```json\n" +
            "{\n" +
            "    \"statements\": [\n" +
            "        {\n" +
            "            \"statement\": \"MATCH p0 = (n0:Entity) MATCH p1 = (n0)<-[:SERVE{CLASS:'GUAR'}]-(n1:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n2:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n3:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n4:Entity) WHERE ID(n0) = 4964691 AND n0 <> n1 AND n0 <> n2 AND n0 <> n3 AND n1 <> n2 AND n1 <> n3 AND n2 <> n3 AND n0 <> n4 AND n1 <> n4 AND n2 <> n4 AND n3 <> n4 AND ALL (r IN RELATIONSHIPS(p1) WHERE r.AMOUNT IS NOT NULL) AND ALL (nn IN NODES(p1) WHERE (nn.IS_FIN_COMP IS NULL)) WITH RELATIONSHIPS(p1) AS rr UNWIND rr AS r WITH DISTINCT ID(STARTNODE(r)) AS SOURCE, ID(ENDNODE(r)) AS TARGET, r.AMOUNT AS AMOUNT, r.RELEASE_DATE AS DATE MERGE (n:TASKRESULTNODE {taskId:'yc-m-task-1-关系网络任务',SOURCE:3,TARGET:2,AMOUNT:1020,DATE:20200531}) return n;\",\n" +
            "        \t\"task-id\":\"yc-m-task-1-关系网络任务-e3f6eec7d9f44b78b242749851a2d922\"\n" +
            "        },\n" +
            "         {\n" +
            "            \"statement\": \"MATCH p0 = (n0:Entity) MATCH p1 = (n0)<-[:SERVE{CLASS:'GUAR'}]-(n1:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n2:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n3:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n4:Entity) WHERE ID(n0) = 4964691 AND n0 <> n1 AND n0 <> n2 AND n0 <> n3 AND n1 <> n2 AND n1 <> n3 AND n2 <> n3 AND n0 <> n4 AND n1 <> n4 AND n2 <> n4 AND n3 <> n4 AND ALL (r IN RELATIONSHIPS(p1) WHERE r.AMOUNT IS NOT NULL) AND ALL (nn IN NODES(p1) WHERE (nn.IS_FIN_COMP IS NULL)) WITH RELATIONSHIPS(p1) AS rr UNWIND rr AS r WITH DISTINCT ID(STARTNODE(r)) AS SOURCE, ID(ENDNODE(r)) AS TARGET, r.AMOUNT AS AMOUNT, r.RELEASE_DATE AS DATE MERGE (n:TASKRESULTNODE {taskId:'yc-m-task-2-关系网络任务',SOURCE:3,TARGET:2,AMOUNT:1020,DATE:20200531}) return n;\",\n" +
            "        \t\"task-id\":\"yc-m-task-2-关系网络任务-e3f6eec7d9f44b78b242749851a2d922\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"user\":\"neo4j\",\n" +
            "    \"password\":\"123456\"\n" +
            "}\n" +
            "```\n" +
            "- 返回值样例\n" +
            "```json\n" +
            "{\n" +
            "    \"error\": null,\n" +
            "    \"result\": {},\n" +
            "    \"code\": 200\n" +
            "}\n" +
            "```")
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





