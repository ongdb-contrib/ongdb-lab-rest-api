# ongdb-lab-rest-api
ONgDB lab rest-api tool kits
>USER CASES:REST-API.md
## READ CYPHER
- /ongdb/read/hello
```
GET /ongdb/read/hello
```
- READ CYPHER提交接口
```
POST /ongdb/read/d/transaction/commit
```
- 查询后台正在运行的task
```
GET /ongdb/read/h/task/query
```
## WRITE CYPHER
- 创建索引
```
POST /ongdb/write/d/transaction/commit
```
- 使用WRITE请求提交后台一个查询任务并将结果集写入ONgDB
```
POST /ongdb/write/d/transaction/commit/task
```

