package com.kenzhe.job.service;

import com.kenzhe.job.exception.review.CompanyNotFoundException;
import com.kenzhe.job.exception.review.JobNotFoundException;
import com.kenzhe.job.mapper.CompanyMapper;
import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.Job;
import com.kenzhe.job.model.dto.CompanyRequestDTO;
import com.kenzhe.job.model.dto.CompanyResponseDTO;
import com.kenzhe.job.repository.CompanyRepository;
import com.kenzhe.job.repository.JobRepository;
import com.kenzhe.job.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    private final JobRepository jobRepository;
    private final CompanyMapper companyMapper;


    public Company createCompany(CompanyRequestDTO companyRequestDTO){
        log.info("Creating a new company");
        return companyRepository.save(companyMapper.toEntity(companyRequestDTO));
    }

    @Transactional(readOnly = true)
    public Company getCompanyById(Long id){
        log.info("Fetching company by id: {}", id);
        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<Company> getAllCompanies(){
        log.info("Fetching all companies");
        return companyRepository.findAll();
    }

    @Transactional
    public Company updateCompanyById(Long id, CompanyRequestDTO companyRequestDTO){
        Company res = getCompanyById(id);
        log.info("Updating a company by ID: {}", id);
        companyMapper.updateEntityFromDto(companyRequestDTO, res);

        log.info("Successfully updated company with id: {}", id);
        companyRepository.save(res);

        return res;
    }



    @Transactional
    public void deleteCompanyById(Long id) {
        if(companyRepository.existsById(id)){
            log.info("Deleting a company by id: {}", id);
            companyRepository.deleteById(id);
        }
        else{
            log.warn("Delete failed. Company not found with id: {}", id);
            throw new CompanyNotFoundException(id);
        }



    }
}
