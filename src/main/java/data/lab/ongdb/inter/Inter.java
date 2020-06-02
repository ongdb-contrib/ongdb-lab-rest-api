package data.lab.ongdb.inter;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONObject;
import data.lab.ongdb.etl.common.CRUD;
import data.lab.ongdb.model.AuthUser;
import data.lab.ongdb.result.Result;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.inter
 * @Description: TODO
 * @date 2020/5/31 13:15
 */
public interface Inter {
    /**
     * @param authUser:校验用户
     * @param cypher:运行只读CYPHER
     * @param crud:操作类型标记
     * @return
     * @Description: TODO(支持只读CYPHER - 返回属性信息必须显式指定)
     */
    Result readAutoCommitCypher(AuthUser authUser, String cypher, CRUD crud);

    /**
     * @param authUser:校验用户
     * @param cypher:要提交任务的CYPHER
     * @param taskId:任务ID-每个CYPHER不可重复-用户自行定义尽量复杂
     * @return
     * @Description: TODO(支持任务提交并写入数据)
     */
    Result writeAutoCommitCypherTask(AuthUser authUser, String cypher, String taskId);

    /**
     * @param authUser:校验用户
     * @return
     * @Description: TODO(查询后台任务)
     */
    Result readTaskQuery(AuthUser authUser);

    /**
     * @param authUser:校验用户
     * @param cypherCondition:http api接口的支持的参数【/db/data/transaction/commit】
     * @return
     * @Description: TODO(查询后台任务)
     */
    Result executeManagerCypherByHttp(AuthUser authUser, JSONObject cypherCondition);
}


