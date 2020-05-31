package data.lab.ongdb.services;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONObject;
import data.lab.ongdb.etl.common.CRUD;
import data.lab.ongdb.etl.common.NeoAccessor;
import data.lab.ongdb.etl.compose.NeoComposer;
import data.lab.ongdb.http.extra.HttpProxyRequest;
import data.lab.ongdb.http.register.OngdbHeartBeat;
import data.lab.ongdb.inter.Inter;

import data.lab.ongdb.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * ONgDB COMPOSER
     **/
    private NeoComposer neoComposer = new NeoComposer();

    private OngdbHeartBeat heartBeat = NeoAccessor.ongdbHeartBeat;

    @Override
    public Result readAutoCommitCypher(String cypher, CRUD crud) {
        JSONObject result = neoComposer.execute(cypher, crud);
        return new Result(200, result);
    }

    @Override
    public Result writeAutoCommitCypherTask(String cypher, String taskId) {
        cypher = cypher.replace("'", "\\'");
        String taskCypher = "CALL apoc.periodic.submit('" + taskId + "','" + cypher + "')";
        JSONObject result = neoComposer.execute(taskCypher, CRUD.MERGE);
        return new Result(200, result);
    }

    /**
     * @param
     * @return
     * @Description: TODO(查询后台任务)
     */
    @Override
    public Result taskQuery() {
        HttpProxyRequest proxyRequest = OngdbHeartBeat.request;
        try {
            String resultStr = proxyRequest.httpPost("/db/data/transaction/commit","{\"statements\": [{\"statement\": \"CALL apoc.periodic.list()\"}]}");
            return new Result(200, JSONObject.parseObject(resultStr));
        } catch (Exception e) {
            return new Result(new String[]{e.getMessage()}, e.hashCode());
        }
    }
}



