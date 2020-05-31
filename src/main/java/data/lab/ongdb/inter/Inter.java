package data.lab.ongdb.inter;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import data.lab.ongdb.etl.common.CRUD;
import data.lab.ongdb.result.Result;

import java.util.List;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.inter
 * @Description: TODO
 * @date 2020/5/31 13:15
 */
public interface Inter {
    /**
     * @param
     * @return
     * @Description: TODO(支持只读CYPHER - 返回属性信息必须显式指定)
     */
    Result readAutoCommitCypher(String cypher, CRUD crud);

    /**
     * @param cypher:要提交任务的CYPHER
     * @param taskId:任务ID-每个CYPHER不可重复-用户自行定义尽量复杂
     * @return
     * @Description: TODO(支持任务提交并写入数据)
     */
    Result writeAutoCommitCypherTask(String cypher, String taskId);

    /**
     * @param
     * @return
     * @Description: TODO(查询后台任务)
     */
    Result taskQuery();
}


