package data.lab.ongdb.services;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import data.lab.ongdb.etl.common.CRUD;
import data.lab.ongdb.etl.compose.NeoComposer;
import data.lab.ongdb.http.extra.HttpProxyRequest;
import data.lab.ongdb.http.register.OngdbHeartBeat;
import data.lab.ongdb.inter.Inter;

import data.lab.ongdb.model.AuthUser;
import data.lab.ongdb.register.Register;
import data.lab.ongdb.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.services.ServiceImpl
 * @Description: TODO
 * @date 2020/5/31 13:45
 */
@Service
public class ServiceImpl implements Inter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);

    /**
     * ONgDB Composer
     **/
    private final static NeoComposer neoComposer = new NeoComposer();

    /**
     * 务必不要使用HttpProxyRequest执行数据的更新写入等操作，仅仅执行读取CYPHER
     **/

    @Override
    public Result readAutoCommitCypher(AuthUser authUser, String cypher, CRUD crud) {
        if (Register.isRegisterOk(authUser)) {
            JSONObject result = new JSONObject();
            try {
                result = neoComposer.execute(cypher, crud);
            } catch (Exception e) {
                return new Result(new String[]{e.getMessage(), result.toJSONString()}, Result.ErrorCode.COMMIT_NO.getCode());
            }
            return new Result(200, result);
        } else {
            return new Result(new String[]{"Auth failed!"}, Result.ErrorCode.AUTH_NO.getCode());
        }
    }

    @Override
    public Result writeAutoCommitCypherTask(AuthUser authUser, String cypher, String taskId) {
        if (Register.isRegisterOk(authUser)) {
            cypher = cypher.replace("'", "\\'");
            String taskCypher = "CALL apoc.periodic.submit('" + taskId + "','" + cypher + "')";
            JSONObject result = new JSONObject();
            try {
                result = neoComposer.execute(taskCypher, CRUD.MERGE_WRITE);
            } catch (Exception e) {
                return new Result(new String[]{e.getMessage(), result.toJSONString()}, Result.ErrorCode.COMMIT_NO.getCode());
            }
            return new Result(200, result);
        } else {
            return new Result(new String[]{"Auth failed!"}, Result.ErrorCode.AUTH_NO.getCode());
        }
    }

    /**
     * @param
     * @return
     * @Description: TODO(查询后台任务)
     */
    @Override
    public Result readTaskQuery(AuthUser authUser) {
        if (Register.isRegisterOk(authUser)) {
            HttpProxyRequest proxyRequest = OngdbHeartBeat.request;
            try {
                String resultStr = proxyRequest.httpPost("/db/data/transaction/commit", "{\"statements\": [{\"statement\": \"CALL apoc.periodic.list()\"}]}");
                return new Result(200, JSONObject.parseObject(resultStr));
            } catch (Exception e) {
                return new Result(new String[]{e.getMessage()}, e.hashCode());
            }
        } else {
            return new Result(new String[]{"Auth failed!"}, Result.ErrorCode.AUTH_NO.getCode());
        }
    }

    /**
     * @param authUser        :校验用户
     * @param cypherCondition
     * @return
     * @Description: TODO(查询后台任务)
     */
    @Override
    public Result executeManagerCypherByHttp(AuthUser authUser, JSONObject cypherCondition) {
        if (Register.isRegisterOk(authUser)) {
            HttpProxyRequest proxyRequest = OngdbHeartBeat.request;
            try {
                String resultStr = proxyRequest.httpPost("/db/data/transaction/commit", cypherCondition.toJSONString());
                return new Result(200, JSONObject.parseObject(resultStr));
            } catch (Exception e) {
                return new Result(new String[]{e.getMessage()}, e.hashCode());
            }
        } else {
            return new Result(new String[]{"Auth failed!"}, Result.ErrorCode.AUTH_NO.getCode());
        }
    }

    /**
     * @param authUser :校验用户
     * @param graphql
     * @return
     * @Description: TODO(执行GraphQL)
     */
    @Override
    public String executeGraphQL(AuthUser authUser, String graphql) {
        if (Register.isRegisterOk(authUser)) {
            HttpProxyRequest proxyRequest = OngdbHeartBeat.request;
            String resultStr = proxyRequest.httpPost("/graphql/", graphql);
            return resultStr;
        } else {
            return JSONObject.parseObject(JSON.toJSONString(new Result(new String[]{"Auth failed!"}, Result.ErrorCode.AUTH_NO.getCode())))
                    .toJSONString();
        }
    }

    /**
     * @param authUser :校验用户
     * @param graphql
     * @return
     * @Description: TODO(执行GraphQL)
     */
    @Override
    public String executeGraphQLEx(AuthUser authUser, String graphql) {
        if (Register.isRegisterOk(authUser)) {
            HttpProxyRequest proxyRequest = OngdbHeartBeat.request;
            String resultStr = proxyRequest.httpPost("/graphql/experimental/", graphql);
            return resultStr;
        } else {
            return JSONObject.parseObject(JSON.toJSONString(new Result(new String[]{"Auth failed!"}, Result.ErrorCode.AUTH_NO.getCode())))
                    .toJSONString();
        }
    }

    /**
     * @param authUser :校验用户
     * @param graphql     :schema参数
     * @return
     * @Description: TODO(创建GraphQL Schema)
     */
    @Override
    public String executeGraphQLIdl(AuthUser authUser, String graphql) {
        if (Register.isRegisterOk(authUser)) {
            HttpProxyRequest proxyRequest = OngdbHeartBeat.request;
            String resultStr = proxyRequest.httpPost("/graphql/idl/", graphql);
            return resultStr;
        } else {
            return JSONObject.parseObject(JSON.toJSONString(new Result(new String[]{"Auth failed!"}, Result.ErrorCode.AUTH_NO.getCode())))
                    .toJSONString();
        }
    }
}



