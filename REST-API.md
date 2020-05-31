# READ CYPHER
- /ongdb/read/hello
```
GET /ongdb/read/hello
```
- READ CYPHER提交接口
```
POST /ongdb/read/d/transaction/commit
{
    "statements": [
        {
            "statement": "MATCH (n) RETURN id(n) AS id,PROPERTIES(n) AS properties LIMIT 1;"
        }
    ]
}
{
    "error": null,
    "code": 200,
    "result": {
        "retrieve_properties": [
            {
                "id": 0,
                "properties": {
                    "score": 0.1515,
                    "STATUS": "False",
                    "UPDATE_DATE": 1392335276,
                    "END_DATE": 20140214,
                    "DATA_SOURCE": "WIND",
                    "CLASS": "BOND",
                    "LISTED_DATE": 20060612,
                    "node_id": "048002_IB_BOND",
                    "NAME": "04首旅债"
                }
            }
        ]
    }
}
```
```
POST /ongdb/read/d/transaction/commit
{
    "statements": [
        {
            "statement": "MATCH (n) RETURN id(n) AS id,PROPERTIES(n) AS properties LIMIT 1;"
        }
    ],
    "return":"retrieve_properties"
}
{
    "error": null,
    "code": 200,
    "result": {
        "retrieve_properties": [
            {
                "id": 0,
                "properties": {
                    "score": 0.1515,
                    "STATUS": "False",
                    "UPDATE_DATE": 1392335276,
                    "END_DATE": 20140214,
                    "DATA_SOURCE": "WIND",
                    "CLASS": "BOND",
                    "LISTED_DATE": 20060612,
                    "node_id": "048002_IB_BOND",
                    "NAME": "04首旅债"
                }
            }
        ]
    }
}
```
```
POST /ongdb/read/d/transaction/commit
{
    "statements": [
        {
            "statement": "MATCH (n) RETURN n LIMIT 1;"
        }
    ]
}
{
    "error": null,
    "code": 200,
    "result": {
        "totalNodeSize": 1,
        "totalRelationSize": 0,
        "results": [
            {
                "data": [
                    {
                        "graph": {
                            "relationships": [],
                            "nodes": [
                                {
                                    "id": 0,
                                    "properties": {
                                        "score": 0.1515,
                                        "STATUS": "False",
                                        "UPDATE_DATE": 1392335276,
                                        "END_DATE": 20140214,
                                        "DATA_SOURCE": "WIND",
                                        "CLASS": "BOND",
                                        "LISTED_DATE": 20060612,
                                        "node_id": "048002_IB_BOND",
                                        "NAME": "04首旅债"
                                    },
                                    "labels": [
                                        "Security"
                                    ]
                                }
                            ],
                            "properties": []
                        }
                    }
                ],
                "columns": [
                    "n"
                ]
            }
        ]
    }
}
```
```
POST /ongdb/read/d/transaction/commit
{
    "statements": [
        {
            "statement": "MATCH p=(n)-[]-(),p2=(m)-[]-() RETURN p,p2 LIMIT 1;"
        }
    ]
}
{
    "error": null,
    "code": 200,
    "result": {
        "totalNodeSize": 4,
        "totalRelationSize": 2,
        "results": [
            {
                "data": [
                    {
                        "graph": {
                            "relationships": [
                                {
                                    "startNode": 1993262,
                                    "id": 28352020,
                                    "type": "ISSUE",
                                    "endNode": 0,
                                    "properties": {
                                        "UPDATE_DATE": 1392335276,
                                        "DATA_SOURCE": "WIND",
                                        "CLASS": "BOND",
                                        "LISTED_DATE": 20060612
                                    }
                                },
                                {
                                    "startNode": 1,
                                    "id": 16816568,
                                    "type": "HOLD",
                                    "endNode": 21341,
                                    "properties": {
                                        "RELEASE_DATE": 20140121,
                                        "REPORT_DATE": 20131231,
                                        "UPDATE_DATE": 1390318655,
                                        "QUANTITY": 1.1E+7,
                                        "DATA_SOURCE": "WIND",
                                        "CLASS": "FUND_HOLD",
                                        "VALUE": 5.511E+7,
                                        "FUNDHOLD_PERCENTAGE": 1.5399999618530273
                                    }
                                }
                            ],
                            "nodes": [
                                {
                                    "id": 1993262,
                                    "properties": {
                                        "REPORT_DATE": 20200113,
                                        "UPDATE_DATE": 1578844800,
                                        "ENG_NAME_SHORT": "BTG",
                                        "ZIPCODE": "100020",
                                        "PHONE": "010-85629988",
                                        "DATA_SOURCE": "CAIHUI",
                                        "BUSINESS": "受市政府委托对国有资产进行经营管理;项目投资;饭店管理;信息咨询;旅游资源开发;旅游服务;房地产项目开发;商品房销售。(企业依法自主选择经营项目,开展经营活动;依法须经批准的项目,经相关部门批准后依批准的内容开展经营活动;不得从事本市产业政策禁止和限制类项目的经营活动。)",
                                        "FOUND_DATE": 19980124,
                                        "CLASS": "Enterprise",
                                        "IS_BOND_COMP": true,
                                        "NAME": "首旅集团",
                                        "REGION": "CN110000",
                                        "ADDR": "北京市朝阳区雅宝路10号凯威大厦3层",
                                        "STATUS": 1,
                                        "CODE": "63369025-9",
                                        "ENG_NAME": "Beijing Tourism Group Co.,Ltd.",
                                        "COUNTRY": "CN",
                                        "PRODUCT": "商品销售、旅行社、酒店及餐饮板块。",
                                        "INTRODUCTION": "    公司前身即北京旅游集团有限责任公司,系根据1998年北京市人民政府京政函[1998]3号文批准,以原北京市旅游局所属的全资、控股、参股企业共计33家组建而成的国有独资有限责任公司,并于1998年1月24日在北京工商行政管理局领取了企业法人营业执照。    2002年12月,根据北京市政府京政办函[2000]157号,旅游集团公司更名为\"北京首都旅游集团有限责任公司\"。",
                                        "FAX": "010-85618080",
                                        "FULLNAME": "北京首都旅游集团有限责任公司",
                                        "WEBSITE": "www.btg.com.cn",
                                        "node_id": "Entity_北京首都旅游集团有限责任公司"
                                    },
                                    "labels": [
                                        "Entity"
                                    ]
                                },
                                {
                                    "id": 0,
                                    "properties": {
                                        "score": 0.1515,
                                        "STATUS": "False",
                                        "UPDATE_DATE": 1392335276,
                                        "END_DATE": 20140214,
                                        "DATA_SOURCE": "WIND",
                                        "CLASS": "BOND",
                                        "LISTED_DATE": 20060612,
                                        "node_id": "048002_IB_BOND",
                                        "NAME": "04首旅债"
                                    },
                                    "labels": [
                                        "Security"
                                    ]
                                },
                                {
                                    "id": 21341,
                                    "properties": {
                                        "STATUS": "True",
                                        "UPDATE_DATE": 1433722345,
                                        "DATA_SOURCE": "WIND",
                                        "CLASS": "STOCK",
                                        "LISTED_DATE": 20080818,
                                        "node_id": "601766_SSE_STOCK",
                                        "NAME": "中国中车"
                                    },
                                    "labels": [
                                        "Security"
                                    ]
                                },
                                {
                                    "id": 1,
                                    "properties": {
                                        "score": 0.15,
                                        "STATUS": "False",
                                        "UPDATE_DATE": 1157674286,
                                        "DATA_SOURCE": "WIND",
                                        "CLASS": "FUND",
                                        "node_id": "483003_OF_FUND",
                                        "NAME": "工银瑞信精选平衡"
                                    },
                                    "labels": [
                                        "Security"
                                    ]
                                }
                            ],
                            "properties": []
                        }
                    }
                ],
                "columns": [
                    "p",
                    "p2"
                ]
            }
        ]
    }
}
```
- 查询后台正在运行的task
```
GET /ongdb/read/h/task/query
```
# WRITE CYPHER
- 创建索引约束
```
POST /ongdb/write/d/transaction/commit
{
    "statements": [
        {
            "statement": "CREATE CONSTRAINT ON (n:TASKRESULTNODE) ASSERT (n.taskId, n.SOURCE,n.TARGET,n.AMOUNT,n.DATE) IS NODE KEY"
        }
    ]
}
```
- 使用WRITE请求提交后台一个查询任务并将结果集写入ONgDB
>【使用此接口获取任务结果/ongdb/read/d/transaction/commit MATCH (n:TASKRESULTNODE) WHERE n.taskId IN ['yc-m-task-1-关系网络任务','yc-m-task-2-关系网络任务'] RETURN n】
```
POST /ongdb/write/d/transaction/commit/task
{
    "statements": [
        {
            "statement": "MATCH p0 = (n0:Entity) MATCH p1 = (n0)<-[:SERVE{CLASS:'GUAR'}]-(n1:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n2:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n3:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n4:Entity) WHERE ID(n0) = 4964691 AND n0 <> n1 AND n0 <> n2 AND n0 <> n3 AND n1 <> n2 AND n1 <> n3 AND n2 <> n3 AND n0 <> n4 AND n1 <> n4 AND n2 <> n4 AND n3 <> n4 AND ALL (r IN RELATIONSHIPS(p1) WHERE r.AMOUNT IS NOT NULL) AND ALL (nn IN NODES(p1) WHERE (nn.IS_FIN_COMP IS NULL)) WITH RELATIONSHIPS(p1) AS rr UNWIND rr AS r WITH DISTINCT ID(STARTNODE(r)) AS SOURCE, ID(ENDNODE(r)) AS TARGET, r.AMOUNT AS AMOUNT, r.RELEASE_DATE AS DATE MERGE (n:TASKRESULTNODE {taskId:'yc-m-task-1-关系网络任务',SOURCE:3,TARGET:2,AMOUNT:1020,DATE:20200531}) return n
;",
        	"task-id":"yc-m-task-1-关系网络任务"
        },
         {
            "statement": "MATCH p0 = (n0:Entity) MATCH p1 = (n0)<-[:SERVE{CLASS:'GUAR'}]-(n1:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n2:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n3:Entity)<-[:SERVE{CLASS:'GUAR'}]-(n4:Entity) WHERE ID(n0) = 4964691 AND n0 <> n1 AND n0 <> n2 AND n0 <> n3 AND n1 <> n2 AND n1 <> n3 AND n2 <> n3 AND n0 <> n4 AND n1 <> n4 AND n2 <> n4 AND n3 <> n4 AND ALL (r IN RELATIONSHIPS(p1) WHERE r.AMOUNT IS NOT NULL) AND ALL (nn IN NODES(p1) WHERE (nn.IS_FIN_COMP IS NULL)) WITH RELATIONSHIPS(p1) AS rr UNWIND rr AS r WITH DISTINCT ID(STARTNODE(r)) AS SOURCE, ID(ENDNODE(r)) AS TARGET, r.AMOUNT AS AMOUNT, r.RELEASE_DATE AS DATE MERGE (n:TASKRESULTNODE {taskId:'yc-m-task-2-关系网络任务',SOURCE:3,TARGET:2,AMOUNT:1020,DATE:20200531}) return n
;",
        	"task-id":"yc-m-task-2-关系网络任务"
        },
        ...
    ]
}
{
    "error": null,
    "result": {},
    "code": 200
}
```

