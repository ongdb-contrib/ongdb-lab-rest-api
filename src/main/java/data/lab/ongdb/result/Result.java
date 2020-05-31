package data.lab.ongdb.result;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONObject;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.result
 * @Description: TODO
 * @date 2020/5/31 13:29
 */
public class Result {
    /**
     * 错误内容
     */
    private String[] error;

    /**
     * 自定义错误码
     */
    private int code;

    /**
     * 执行结果
     */
    private JSONObject result;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, JSONObject result) {
        this.code = code;
        this.result = result;
    }

    public Result(String[] error, int code, JSONObject result) {
        this.error = error;
        this.code = code;
        this.result = result;
    }

    public Result(String[] error, int code) {
        this.error = error;
        this.code = code;
    }

    public String[] getError() {
        return error;
    }

    public void setError(String[] error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public enum ErrorCode {
        /**
         * CYPHER提交成功
         */
        COMMIT_YES(40401),

        /**
         * CYPHER提交失败
         */
        COMMIT_NO(40001);

        private int code;

        public int getCode() {
            return code;
        }

        ErrorCode(int code) {
            this.code = code;
        }
    }
}

