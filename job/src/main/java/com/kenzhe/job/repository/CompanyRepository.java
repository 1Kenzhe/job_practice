package com.kenzhe.job.repository;

import com.kenzhe.job.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query("SELECT COUNT(j) FROM Job j WHERE j.company.id = :companyId")
    int countJobsByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT COUNT(r) FROM Review r WHERE r.company.id = :companyId")
    int countReviewsByCompanyId(@Param("companyId") Long companyId);
}
