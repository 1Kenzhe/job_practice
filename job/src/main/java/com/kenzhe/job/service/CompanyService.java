package com.kenzhe.job.service;

import com.kenzhe.job.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

    Company createCompany(Company company);

    Company updateCompanyById(Long id, Company company);

    boolean deleteCompanyById(Long id);

}
