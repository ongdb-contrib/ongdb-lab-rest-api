package data.lab.ongdb.controller;
/*
 *
 * Data Lab - graph database organization.
 *
 */
import data.lab.ongdb.services.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.controller
 * @Description: TODO(GraphQL端点)
 * @date 2020/7/8 21:20
 */
@Controller
@RequestMapping("/graph")
/**
 * 支持跨源请求
 * **/
@CrossOrigin(origins = "*", maxAge = 3600)
public class GraphController {

    private final static Logger LOGGER = LoggerFactory.getLogger(GraphController.class);

    /**
     * 注册数据逻辑处理类
     **/
    @Autowired
    protected ServiceImpl dataService;

    /**
     * @param
     * @return
     * @Description: TODO(修改数据)
     */
    @RequestMapping(value = "/graphql", method = RequestMethod.POST)
    @ResponseBody
    public String hTransactionCommit(@RequestBody String para) {
        return dataService.executeGraphQL(para);
    }

    /**
     * @param
     * @return
     * @Description: TODO(查数据)
     */
    @RequestMapping(value = "/graphql/experimental", method = RequestMethod.POST)
    @ResponseBody
    public String executeGraphQlEx(@RequestBody String para) {
        return dataService.executeGraphQLEx(para);
    }
}

