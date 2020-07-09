package data.lab.ongdb.graphql;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyGraphQLDataFetchers {

    @Autowired
    private CompanyService companyService;

    /**
     * @param
     * @return
     * @Description: TODO
     *
     * input:
     * {
     *   companyById(id: "213") {
     *     id
     *     name
     *     age
     *   }
     * }
     *
     *
     * output:
     * {
     *   "data": {
     *     "companyById": {
     *       "id": "213",
     *       "name": "tsad",
     *       "age": 12
     *     }
     *   }
     * }
     *
     */
    public DataFetcher getCompanyByIdDataFetcher() {
        List<Company> companies = companyService.getCompanies();
        return dataFetchingEnvironment -> {
            String companyId = dataFetchingEnvironment.getArgument("id");
            return companies
                    .stream()
                    .filter(company -> company.getId().equals(companyId))
                    .findFirst()
                    .orElse(null);
        };
    }

}

