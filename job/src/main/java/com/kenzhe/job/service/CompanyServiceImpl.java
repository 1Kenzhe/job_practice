package com.kenzhe.job.service;

import com.kenzhe.job.exception.review.CompanyNotFoundException;
import com.kenzhe.job.model.Company;
import com.kenzhe.job.repository.CompanyRepository;
import com.kenzhe.job.repository.JobRepository;
import com.kenzhe.job.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final JobRepository jobRepository;


    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    public Company getCompanyById(Long id){
        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company updateCompanyById(Long id, Company company){
        Company res = getCompanyById(id);
        res.setName(company.getName());
        res.setDescription(company.getDescription());

        if(res != null) {
            res.setReviews(company.getReviews());
            res.setJobs(company.getJobs());
        }
        companyRepository.save(res);
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
