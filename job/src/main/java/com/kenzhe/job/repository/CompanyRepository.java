package com.kenzhe.job.repository;

import com.kenzhe.job.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
