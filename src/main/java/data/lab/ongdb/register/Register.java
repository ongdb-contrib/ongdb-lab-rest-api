package data.lab.ongdb.register;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import com.alibaba.fastjson.JSONObject;
import data.lab.ongdb.etl.common.NeoAccessor;
import data.lab.ongdb.etl.driver.BuildDriver;
import data.lab.ongdb.etl.util.FileUtil;
import data.lab.ongdb.model.AuthUser;
import org.neo4j.driver.Driver;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.register
 * @Description: TODO
 * @date 2020/6/1 13:08
 */
public class Register {

    private final static Map<AuthUser, Driver> authUserDriverMap = new HashMap<>();

    private static List<AuthUser> authUserList;
    private static Driver driver;

    static {
        String authStr = FileUtil.readAllLine("conf" + File.separator + "auth.json", "UTF-8");
        authUserList = JSONObject.parseArray(authStr, AuthUser.class);
        AuthUser defaultUser = authUserList.stream().filter(user->"neo4j".equals(user.getUser())).findFirst().get();
        driver = BuildDriver.build(defaultUser.getUser(), defaultUser.getPassword());
        // 取消DRIVER端用户认证
//        for (AuthUser authUser : authUserList) {
//            Driver authDriver = BuildDriver.build(authUser.getUser(), authUser.getPassword());
//            authUserDriverMap.put(authUser, authDriver);
//        }
    }

    public static boolean isRegisterOk(AuthUser authUser) {
        NeoAccessor.driverIsResetDriver = true;
        NeoAccessor.driver = authUserDriverMap.getOrDefault(authUser, driver);
        return authUserList.contains(authUser);
    }

}


