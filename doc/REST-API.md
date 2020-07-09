# 接口核心功能
>【服务负载均衡】【用户访问安全认证】【服务健康检查】【服务容灾故障恢复】【服务自动发现】【分组服务管理】【自动路由】【读写分离】【主机名域名服务映射】

## 集成说明
>单机直接调用即可，集群情况下可使用Nginx服务集成，保证应用的高可用性

## 二次开发实现细节注意点
>【接口设计为主要分为READ和WRITE】【接口地址中/h/与/d/分别表示使用【HTTP API和JAVA DRIVER】底层驱动和ONgDB交互】【自动识别返回的节点关系属性等等信息针对性的进行统一数据封装】【长连接底层不可以使用HTTP API交互，必须使用BOLT协议】【在请求参数中使用密码认证】【数据更新删除新建等操作只能在LEADER节点执行】

# 接口说明
- /ongdb/read/d/transaction/commit:运行只读查询，使用readTransaction提交【查询中不能有数据更改操作】
- /ongdb/read/h/task/query:查看后台正在运行的TASK，使用HTTP API提交
- /ongdb/read/h/transaction/commit:http支持的所有查询【例如提交任务取消CYPHER】
- /ongdb/write/d/transaction/commit:运行新建删除更新等查询，使用writeTransaction提交
- /ongdb/write/d/transaction/commit/task:运行后台任务可支持数据直接写入更新等操作，使用writeTransaction提交【集群内任务不支持自动分发只能在LEADER节点执行】
- /ongdb/write/hello:运行hello world不需要用户验证，无提交方式

## CODE - 状态码
>200/201/202/203/204都表示请求成功，其它代码则表示接口调用失败
>接口程序默认启用http（7424）、https（7425）两个端口

## GET请求中特殊参数替换方式【不替换服务端无法正常识别例如带有百分号的用户密码需要替换成%25】
| 符号 | 说明                       | 替换方式 |
| :--- | :------------------------ | :------ |
| +    | URL 中+号表示空格           | %2B     |
| 空格 | URL中的空格可以用+号或者编码 | %20     |
| /    | 分隔目录和子目录            | %2F     |
| ?    | 分隔实际的URL和参数         | %3F     |
| %    | 指定特殊字符                | %25     |
| #    | 表示书签                   | %23     |
| &    | URL 中指定的参数间的分隔符   | %26     |
| =    | URL 中指定参数的值          | %3D     |

### POST /ongdb/graph/graphql/experimental/
- 参数样例
- GraphQL APP
```
graphql: query {column(name:"ods.test_table.c1") {name}}
```
- HTTP APP
```
{"query":"query {column(name:\"ods.test_table.c1\") {name}}","variables":null}
```
- 返回值
```
{"data":[{"column":{"name":"ods.test_table.c1"}}]}
```
### POST /ongdb/graph/graphql/
- 参数样例
- GraphQL APP
```
graphql: mutation {createColumn(name: "The Shape of Water")}
```
- HTTP APP
```
{"query":"mutation {\n  createColumn(name: \"The Shape of Water\")\n}\n","variables":null}
```
- 返回值
```
{"data":{"createColumn":"Nodes created: 1\nProperties set: 1\nLabels added: 1\n"}}
```

### GET /ongdb/graphiql
```
格式化GraphQL
```

