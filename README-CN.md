# ONgDB REST API
>负责CYPHER请求的分发，负载均衡，增删改查等。
- 接口程序默认启用http（7424）、https（7425）两个端口

## 统一Restful接口设计
```
GET（SELECT）：从服务器取出资源（一项或多项）。
POST（CREATE）：在服务器新建一个资源。
PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
DELETE（DELETE）：从服务器删除资源。

// 访问成功 - 访问失败返回其它代码即可
200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
204 NO CONTENT - [DELETE]：用户删除数据成功。

```
- 接口设计为主要分为READ和WRITE
- 接口地址中/h/与/d/分别表示使用哪种底层驱动和ONgDB交互
- 自动识别返回的节点关系属性等等信息针对性的进行统一数据封装
- 接口参数的接收和返回数据的格式均参考HTTP API格式【参考HTTP.md文件】
- 长连接底层不可以使用HTTP API交互，必须使用BOLT协议

## LUCENE语法
1、基础的查询语法，关键词查询：

　　　域名+“：”+搜索的关键字

　　　例如：content:java

2、范围查询

　　　域名+“:”+[最小值 TO 最大值]

　　　例如：size:[1 TO 1000]

　　　范围查询在lucene中支持数值类型，不支持字符串类型。在solr中支持字符串类型。

3、组合条件查询

　1）+条件1 +条件2：两个条件之间是并且的关系and

　　　例如：+filename:apache +content:apache

　2）+条件1 条件2：必须满足第一个条件，应该满足第二个条件

　　　例如：+filename:apache content:apache

　3）条件1 条件2：两个条件满足其一即可。

　　　例如：filename:apache content:apache

　4）-条件1 条件2：必须不满足条件1，要满足条件2

　　　例如：-filename:apache content:apache
 
 | Occur.MUST 查询条件必须满足，相当于and | +（加号） |
 | ------ | ------ |
 | Occur.SHOULD 查询条件可选，相当于or | 空（不用符号） |
 | Occur.MUST_NOT 查询条件不能满足，相当于not非 | -（减号） |
 
 4、组合条件查询
  
  　　条件1 AND 条件2 / 条件1 OR 条件2 / 条件1 NOT 条件2
  
 5、表达式分组
 
    表示必须为男性，且括号中条件必须满足一个
    
    例: +(FacebookID.locality:BeiJing FacebookID.ReligiousViews:Methodist) +FacebookID.Gender:男
    
    
 

