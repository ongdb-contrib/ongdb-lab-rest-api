package data.lab.ongdb.graphql;
/*
 *
 * Data Lab - graph database organization.
 *
 */

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {


    @Override
    public List<Company> getCompanies() {
        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company("213","tsad",12,"address",9.012,"2020-01"));
        companyList.add(new Company("21","tsad",15,"address",7.012,"2020-02"));
        return companyList;
    }
}

