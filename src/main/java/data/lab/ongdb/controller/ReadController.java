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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@Api(value = "read",description = "执行只读CYPHER")
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
    @ApiOperation(value = "READ CYPHER提交接口",notes = "- 参数样例1：\n" +
            "```json\n" +
            "{\n" +
            "    \"statements\": [\n" +
            "        {\n" +
            "            \"statement\": \"MATCH (n) RETURN id(n) AS id,PROPERTIES(n) AS properties LIMIT 1;\"\n" +
            "        }\n" +
            "    ],\n" +
            "     \"user\":\"neo4j\",\n" +
            "    \"password\":\"123456\"\n" +
            "}\n" +
            "```\n" +
            "- 返回值样例1：\n" +
            "```json\n" +
            "{\n" +
            "    \"error\": null,\n" +
            "    \"code\": 200,\n" +
            "    \"result\": {\n" +
            "        \"retrieve_properties\": [\n" +
            "            {\n" +
            "                \"id\": 0,\n" +
            "                \"properties\": {\n" +
            "                    \"score\": 0.1515,\n" +
            "                    \"STATUS\": \"False\",\n" +
            "                    \"UPDATE_DATE\": 1392335276,\n" +
            "                    \"END_DATE\": 20140214,\n" +
            "                    \"DATA_SOURCE\": \"WIND\",\n" +
            "                    \"CLASS\": \"BOND\",\n" +
            "                    \"LISTED_DATE\": 20060612,\n" +
            "                    \"node_id\": \"048002_IB_BOND\",\n" +
            "                    \"NAME\": \"04首旅债\"\n" +
            "                }\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}\n" +
            "```\n" +
            "- 参数样例2\n" +
            "```json\n" +
            "{\n" +
            "    \"statements\": [\n" +
            "        {\n" +
            "            \"statement\": \"MATCH (n) RETURN id(n) AS id,PROPERTIES(n) AS properties LIMIT 1;\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"return\":\"retrieve_properties\",\n" +
            "    \"user\":\"neo4j\",\n" +
            "    \"password\":\"123456\"\n" +
            "}\n" +
            "```\n" +
            "- 返回值样例2\n" +
            "```json\n" +
            "{\n" +
            "    \"error\": null,\n" +
            "    \"code\": 200,\n" +
            "    \"result\": {\n" +
            "        \"retrieve_properties\": [\n" +
            "            {\n" +
            "                \"id\": 0,\n" +
            "                \"properties\": {\n" +
            "                    \"score\": 0.1515,\n" +
            "                    \"STATUS\": \"False\",\n" +
            "                    \"UPDATE_DATE\": 1392335276,\n" +
            "                    \"END_DATE\": 20140214,\n" +
            "                    \"DATA_SOURCE\": \"WIND\",\n" +
            "                    \"CLASS\": \"BOND\",\n" +
            "                    \"LISTED_DATE\": 20060612,\n" +
            "                    \"node_id\": \"048002_IB_BOND\",\n" +
            "                    \"NAME\": \"04首旅债\"\n" +
            "                }\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}\n" +
            "```\n" +
            "- 参数样例3\n" +
            "```json\n" +
            "{\n" +
            "    \"statements\": [\n" +
            "        {\n" +
            "            \"statement\": \"MATCH (n) RETURN n LIMIT 1;\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"user\":\"neo4j\",\n" +
            "    \"password\":\"123456\"\n" +
            "}\n" +
            "```\n" +
            "- 返回值样例3\n" +
            "```json\n" +
            "{\n" +
            "    \"error\": null,\n" +
            "    \"code\": 200,\n" +
            "    \"result\": {\n" +
            "        \"totalNodeSize\": 1,\n" +
            "        \"totalRelationSize\": 0,\n" +
            "        \"results\": [\n" +
            "            {\n" +
            "                \"data\": [\n" +
            "                    {\n" +
            "                        \"graph\": {\n" +
            "                            \"relationships\": [],\n" +
            "                            \"nodes\": [\n" +
            "                                {\n" +
            "                                    \"id\": 0,\n" +
            "                                    \"properties\": {\n" +
            "                                        \"score\": 0.1515,\n" +
            "                                        \"STATUS\": \"False\",\n" +
            "                                        \"UPDATE_DATE\": 1392335276,\n" +
            "                                        \"END_DATE\": 20140214,\n" +
            "                                        \"DATA_SOURCE\": \"WIND\",\n" +
            "                                        \"CLASS\": \"BOND\",\n" +
            "                                        \"LISTED_DATE\": 20060612,\n" +
            "                                        \"node_id\": \"048002_IB_BOND\",\n" +
            "                                        \"NAME\": \"04首旅债\"\n" +
            "                                    },\n" +
            "                                    \"labels\": [\n" +
            "                                        \"Security\"\n" +
            "                                    ]\n" +
            "                                }\n" +
            "                            ],\n" +
            "                            \"properties\": []\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"columns\": [\n" +
            "                    \"n\"\n" +
            "                ]\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "}\n" +
            "```\n" +
            "- 参数样例4\n" +
            "```json\n" +
            "{\n" +
            "    \"statements\": [\n" +
            "        {\n" +
            "            \"statement\": \"MATCH p=(n)-[]-(),p2=(m)-[]-() RETURN p,p2 LIMIT 1;\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"user\":\"neo4j\",\n" +
            "    \"password\":\"123456\"\n" +
            "}\n" +
            "```\n" +
            "- 返回值样例4\n" +
            "```json\n" +
            "{\n" +
            "    \"error\": null,\n" +
            "    \"code\": 200,\n" +
            "    \"result\": {\n" +
            "        \"totalNodeSize\": 4,\n" +
            "        \"totalRelationSize\": 2,\n" +
            "        \"results\": [\n" +
            "            {\n" +
            "                \"data\": [\n" +
            "                    {\n" +
            "                        \"graph\": {\n" +
            "                            \"relationships\": [\n" +
            "                                {\n" +
            "                                    \"startNode\": 1993262,\n" +
            "                                    \"id\": 28352020,\n" +
            "                                    \"type\": \"ISSUE\",\n" +
            "                                    \"endNode\": 0,\n" +
            "                                    \"properties\": {\n" +
            "                                        \"UPDATE_DATE\": 1392335276,\n" +
            "                                        \"DATA_SOURCE\": \"WIND\",\n" +
            "                                        \"CLASS\": \"BOND\",\n" +
            "                                        \"LISTED_DATE\": 20060612\n" +
            "                                    }\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"startNode\": 1,\n" +
            "                                    \"id\": 16816568,\n" +
            "                                    \"type\": \"HOLD\",\n" +
            "                                    \"endNode\": 21341,\n" +
            "                                    \"properties\": {\n" +
            "                                        \"RELEASE_DATE\": 20140121,\n" +
            "                                        \"REPORT_DATE\": 20131231,\n" +
            "                                        \"UPDATE_DATE\": 1390318655,\n" +
            "                                        \"QUANTITY\": 1.1E+7,\n" +
            "                                        \"DATA_SOURCE\": \"WIND\",\n" +
            "                                        \"CLASS\": \"FUND_HOLD\",\n" +
            "                                        \"VALUE\": 5.511E+7,\n" +
            "                                        \"FUNDHOLD_PERCENTAGE\": 1.5399999618530273\n" +
            "                                    }\n" +
            "                                }\n" +
            "                            ],\n" +
            "                            \"nodes\": [\n" +
            "                                {\n" +
            "                                    \"id\": 1993262,\n" +
            "                                    \"properties\": {\n" +
            "                                        \"REPORT_DATE\": 20200113,\n" +
            "                                        \"UPDATE_DATE\": 1578844800,\n" +
            "                                        \"ENG_NAME_SHORT\": \"BTG\",\n" +
            "                                        \"ZIPCODE\": \"100020\",\n" +
            "                                        \"PHONE\": \"010-85629988\",\n" +
            "                                        \"DATA_SOURCE\": \"CAIHUI\",\n" +
            "                                        \"BUSINESS\": \"受市政府委托对国有资产进行经营管理;项目投资;饭店管理;信息咨询;旅游资源开发;旅游服务;房地产项目开发;商品房销售。(企业依法自主选择经营项目,开展经营活动;依法须经批准的项目,经相关部门批准后依批准的内容开展经营活动;不得从事本市产业政策禁止和限制类项目的经营活动。)\",\n" +
            "                                        \"FOUND_DATE\": 19980124,\n" +
            "                                        \"CLASS\": \"Enterprise\",\n" +
            "                                        \"IS_BOND_COMP\": true,\n" +
            "                                        \"NAME\": \"首旅集团\",\n" +
            "                                        \"REGION\": \"CN110000\",\n" +
            "                                        \"ADDR\": \"北京市朝阳区雅宝路10号凯威大厦3层\",\n" +
            "                                        \"STATUS\": 1,\n" +
            "                                        \"CODE\": \"63369025-9\",\n" +
            "                                        \"ENG_NAME\": \"Beijing Tourism Group Co.,Ltd.\",\n" +
            "                                        \"COUNTRY\": \"CN\",\n" +
            "                                        \"PRODUCT\": \"商品销售、旅行社、酒店及餐饮板块。\",\n" +
            "                                        \"INTRODUCTION\": \"    公司前身即北京旅游集团有限责任公司,系根据1998年北京市人民政府京政函[1998]3号文批准,以原北京市旅游局所属的全资、控股、参股企业共计33家组建而成的国有独资有限责任公司,并于1998年1月24日在北京工商行政管理局领取了企业法人营业执照。    2002年12月,根据北京市政府京政办函[2000]157号,旅游集团公司更名为\\\"北京首都旅游集团有限责任公司\\\"。\",\n" +
            "                                        \"FAX\": \"010-85618080\",\n" +
            "                                        \"FULLNAME\": \"北京首都旅游集团有限责任公司\",\n" +
            "                                        \"WEBSITE\": \"www.btg.com.cn\",\n" +
            "                                        \"node_id\": \"Entity_北京首都旅游集团有限责任公司\"\n" +
            "                                    },\n" +
            "                                    \"labels\": [\n" +
            "                                        \"Entity\"\n" +
            "                                    ]\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"id\": 0,\n" +
            "                                    \"properties\": {\n" +
            "                                        \"score\": 0.1515,\n" +
            "                                        \"STATUS\": \"False\",\n" +
            "                                        \"UPDATE_DATE\": 1392335276,\n" +
            "                                        \"END_DATE\": 20140214,\n" +
            "                                        \"DATA_SOURCE\": \"WIND\",\n" +
            "                                        \"CLASS\": \"BOND\",\n" +
            "                                        \"LISTED_DATE\": 20060612,\n" +
            "                                        \"node_id\": \"048002_IB_BOND\",\n" +
            "                                        \"NAME\": \"04首旅债\"\n" +
            "                                    },\n" +
            "                                    \"labels\": [\n" +
            "                                        \"Security\"\n" +
            "                                    ]\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"id\": 21341,\n" +
            "                                    \"properties\": {\n" +
            "                                        \"STATUS\": \"True\",\n" +
            "                                        \"UPDATE_DATE\": 1433722345,\n" +
            "                                        \"DATA_SOURCE\": \"WIND\",\n" +
            "                                        \"CLASS\": \"STOCK\",\n" +
            "                                        \"LISTED_DATE\": 20080818,\n" +
            "                                        \"node_id\": \"601766_SSE_STOCK\",\n" +
            "                                        \"NAME\": \"中国中车\"\n" +
            "                                    },\n" +
            "                                    \"labels\": [\n" +
            "                                        \"Security\"\n" +
            "                                    ]\n" +
            "                                },\n" +
            "                                {\n" +
            "                                    \"id\": 1,\n" +
            "                                    \"properties\": {\n" +
            "                                        \"score\": 0.15,\n" +
            "                                        \"STATUS\": \"False\",\n" +
            "                                        \"UPDATE_DATE\": 1157674286,\n" +
            "                                        \"DATA_SOURCE\": \"WIND\",\n" +
            "                                        \"CLASS\": \"FUND\",\n" +
            "                                        \"node_id\": \"483003_OF_FUND\",\n" +
            "                                        \"NAME\": \"工银瑞信精选平衡\"\n" +
            "                                    },\n" +
            "                                    \"labels\": [\n" +
            "                                        \"Security\"\n" +
            "                                    ]\n" +
            "                                }\n" +
            "                            ],\n" +
            "                            \"properties\": []\n" +
            "                        }\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"columns\": [\n" +
            "                    \"p\",\n" +
            "                    \"p2\"\n" +
            "                ]\n" +
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
    @ApiOperation(value = "/ongdb/read/h/task/query?user=neo4j&password=123456 查询后台正在运行的task",notes = "- 返回值样例\n" +
            "```json\n" +
            "{\n" +
            "    \"error\": null,\n" +
            "    \"code\": 200,\n" +
            "    \"result\": {\n" +
            "        \"results\": [\n" +
            "            {\n" +
            "                \"data\": [\n" +
            "                    {\n" +
            "                        \"meta\": [\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null\n" +
            "                        ],\n" +
            "                        \"row\": [\n" +
            "                            \"公司主实体节点生成相似简介关系的任务\",\n" +
            "                            0,\n" +
            "                            0,\n" +
            "                            false,\n" +
            "                            false\n" +
            "                        ]\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"meta\": [\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null\n" +
            "                        ],\n" +
            "                        \"row\": [\n" +
            "                            \"公司主实体节点生成相似业务关系的任务\",\n" +
            "                            0,\n" +
            "                            0,\n" +
            "                            false,\n" +
            "                            false\n" +
            "                        ]\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"meta\": [\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null\n" +
            "                        ],\n" +
            "                        \"row\": [\n" +
            "                            \"公司主实体聚类任务\",\n" +
            "                            0,\n" +
            "                            0,\n" +
            "                            false,\n" +
            "                            false\n" +
            "                        ]\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"meta\": [\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null\n" +
            "                        ],\n" +
            "                        \"row\": [\n" +
            "                            \"公司主实体节点生成相似名称关系的任务\",\n" +
            "                            0,\n" +
            "                            0,\n" +
            "                            false,\n" +
            "                            false\n" +
            "                        ]\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"columns\": [\n" +
            "                    \"name\",\n" +
            "                    \"delay\",\n" +
            "                    \"rate\",\n" +
            "                    \"done\",\n" +
            "                    \"cancelled\"\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"errors\": []\n" +
            "    }\n" +
            "}\n" +
            "```\n")
    public Result taskQuery(AuthUser authUser) {
        return dataService.readTaskQuery(authUser);
    }

    /**
     * @param para
     * @return
     * @Description: TODO(事务自动提交)
     */
    @RequestMapping(value = "/h/transaction/commit", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "【取消运行的任务】",notes = "-参数样例\n" +
            "```json\n" +
            "{\n" +
            "    \"statements\": [\n" +
            "        {\n" +
            "            \"statement\": \"CALL apoc.periodic.cancel('writeTest');\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"user\":\"neo4j\",\n" +
            "    \"password\":\"123456\"\n" +
            "}\n" +
            "```\n" +
            "-返回值样例\n" +
            "```\n" +
            "{\n" +
            "    \"error\": null,\n" +
            "    \"code\": 200,\n" +
            "    \"result\": {\n" +
            "        \"results\": [\n" +
            "            {\n" +
            "                \"data\": [\n" +
            "                    {\n" +
            "                        \"meta\": [\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null,\n" +
            "                            null\n" +
            "                        ],\n" +
            "                        \"row\": [\n" +
            "                            \"writeTest\",\n" +
            "                            0,\n" +
            "                            0,\n" +
            "                            true,\n" +
            "                            true\n" +
            "                        ]\n" +
            "                    }\n" +
            "                ],\n" +
            "                \"columns\": [\n" +
            "                    \"name\",\n" +
            "                    \"delay\",\n" +
            "                    \"rate\",\n" +
            "                    \"done\",\n" +
            "                    \"cancelled\"\n" +
            "                ]\n" +
            "            }\n" +
            "        ],\n" +
            "        \"errors\": []\n" +
            "    }\n" +
            "}\n" +
            "```")
    public Result hTransactionCommit(@RequestBody JSONObject para) {
        Result result;
        try {
            String user = para.getString("user");
            String password = para.getString("password");
            para.remove("user");
            para.remove("password");
            return dataService.executeManagerCypherByHttp(new AuthUser(user, password), para);
        } catch (IllegalArgumentException e) {
            result = new Result(new String[]{e.getMessage()}, e.hashCode());
        }
        return result;
    }
}





