package com.kenzhe.job.repository;


import com.kenzhe.job.model.Job;
import com.kenzhe.job.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByCompanyId(Long companyId);
    Optional<Job> findByCompanyIdAndId(Long companyId, Long jobId);
}
