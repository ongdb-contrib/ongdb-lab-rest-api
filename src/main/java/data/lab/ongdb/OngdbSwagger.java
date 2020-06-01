package data.lab.ongdb;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import data.lab.ongdb.etl.util.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;

/**
 * @author Yc-Ma
 * @PACKAGE_NAME: data.lab.ongdb.swagger
 * @Description: TODO(Swagger API文档配置属性类)
 * @date 2020/6/1 16:39
 */
@Configuration
@EnableSwagger2
public class OngdbSwagger {
    @Bean
    public Docket createSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("data.lab.ongdb"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * http://localhost:7424/ongdb/v2/api-docs
     * https://localhost:7425/ongdb/v2/api-docs
     * http://localhost:7424/ongdb/swagger-ui.html
     * https://localhost:7425/ongdb/swagger-ui.html
     *
     * @param
     * @return
     * @Description: TODO
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                /*页面标题*/
                .title("DataLab图数据平台API服务 - ONgDB Restful Api Document")
                /*创建人*/
                .contact(new Contact("Yc-Ma", "#", "#"))
                /*页面标题*/
                .description(new StringBuilder()
                        /**********************基础描述**********************/
                        .append("Data Lab - graph database organization.")
                        .append("\n")
                        /**********************接口实现细节注意点**********************/
                        .append("# 接口核心功能")
                        .append("\n")
                        .append("【服务负载均衡】")
                        .append("【用户访问安全认证】")
                        .append("【服务健康检查】")
                        .append("【服务容灾故障恢复】")
                        .append("【服务自动发现】")
                        .append("【分组服务管理】")
                        .append("【自动路由】")
                        .append("【读写分离】")
                        .append("【主机名域名服务映射】")
                        .append("\n")
                        /**********************接口实现细节注意点**********************/
                        .append("# 接口实现细节注意点")
                        .append("\n")
                        .append("【接口设计为主要分为READ和WRITE】")
                        .append("【接口地址中/h/与/d/分别表示使用【HTTP API和JAVA DRIVER】底层驱动和ONgDB交互】")
                        .append("【自动识别返回的节点关系属性等等信息针对性的进行统一数据封装】")
                        .append("【长连接底层不可以使用HTTP API交互，必须使用BOLT协议】")
                        .append("【在请求参数中使用密码认证】")
                        .append("【数据更新删除新建等操作只能在LEADER节点执行】")
                        .append("\n")
                        /**********************接口说明**********************/
                        .append("# 接口说明")
                        .append("\n")
                        .append(readControllerApiDescription())
                        .append(writeControllerApiDescription())
                        .append(apiDescription())
                        .toString())
                /*版本号*/
                .version("1.2")
                .build();
    }

    /**
     * @param
     * @return
     * @Description: TODO(READ CONTROLLER接口说明)
     */
    private String readControllerApiDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("- /read/d/transaction/commit:运行只读查询，使用readTransaction提交【查询中不能有数据更改操作】");
        builder.append("\n");
        builder.append("- /read/h/task/query:查看后台正在运行的TASK，使用HTTP API提交");
        builder.append("\n");
        return builder.toString();
    }

    /**
     * @param
     * @return
     * @Description: TODO(WRITE CONTROLLER接口说明))
     */
    private String writeControllerApiDescription() {
        StringBuilder builder = new StringBuilder();
        builder.append("- /write/d/transaction/commit:运行新建删除更新等查询，使用writeTransaction提交");
        builder.append("\n");
        builder.append("- /write/d/transaction/commit/task:运行后台任务可支持数据直接写入更新等操作，使用writeTransaction提交【集群内任务不支持自动分发只能在LEADER节点执行】");
        builder.append("\n");
        builder.append("- /write/hello:运行hello world不需要用户验证，无提交方式");
        builder.append("\n");
        return builder.toString();
    }

    /**
     * @param
     * @return
     * @Description: TODO(加载接口的所有详细说明)
     */
    private String apiDescription() {
        return FileUtil.readAllLine("doc"+ File.separator+"REST-API.md", "UTF-8");
    }
}


