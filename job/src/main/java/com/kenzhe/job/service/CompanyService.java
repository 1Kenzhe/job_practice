package com.kenzhe.job.service;

import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.dto.CompanyRequestDTO;
import com.kenzhe.job.model.dto.CompanyResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {

    Company getCompanyById(Long id);

    List<Company> getAllCompanies();

    Company createCompany(CompanyRequestDTO companyRequestDTO);

    Company updateCompanyById(Long id, CompanyRequestDTO companyRequestDTO);

    void deleteCompanyById(Long id);

}
