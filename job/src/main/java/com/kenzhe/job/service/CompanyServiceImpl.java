package com.kenzhe.job.service;

import com.kenzhe.job.model.Company;
import com.kenzhe.job.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElse(null);
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company updateCompanyById(Long id, Company company){
        Company res = getCompanyById(id);
        if(res != null) {
            res.setName(company.getName());
            res.setDescription(company.getDescription());
            res.setJobs(company.getJobs());
            companyRepository.save(res);
        }
        return res;
    }

    public boolean deleteCompanyById(Long id) {
        try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
