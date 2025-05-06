package com.kenzhe.job.service;

import com.kenzhe.job.exception.review.JobNotFoundException;
import com.kenzhe.job.exception.review.ReviewNotFoundException;
import com.kenzhe.job.mapper.JobMapper;
import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.Job;
import com.kenzhe.job.model.Review;
import com.kenzhe.job.model.dto.JobRequestDTO;
import com.kenzhe.job.repository.CompanyRepository;
import com.kenzhe.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final JobMapper jobMapper;

    public List<Job> getAllJobs(Long companyId) {
        log.info("Fetching all jobs");
        return jobRepository.findByCompanyId(companyId);
    }

    public Job getJobById(Long companyId, Long jobId) {
        log.info("Fetching company by ID and job by ID: {} and {}", companyId, jobId);
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        return jobs.stream()
            .filter(job -> job.getId().equals(jobId))
            .findFirst()
            .orElseThrow(() -> new JobNotFoundException(companyId, jobId));
    }

    public Job createJob(JobRequestDTO jobRequestDTO) {
        log.info("Creating a new company");
        return jobRepository.save(jobMapper.toEntity(jobRequestDTO));
    }

    public Job updateJob(Long companyId, Long jobId, JobRequestDTO jobRequestDTO) {
        log.info("Updating company by ID and job by ID: {} and {}", companyId, jobId);
        Job res = jobRepository.findByCompanyIdAndId(companyId, jobId)
                                .orElseThrow(() -> new JobNotFoundException(companyId, jobId));

        jobMapper.updateEntityFromDto(jobRequestDTO, res);
//        res.setTitle(jobRequestDTO.getTitle());
//        res.setDescription(jobRequestDTO.getDescription());
//        res.setMinSalary(jobRequestDTO.getMinSalary());
//        res.setMaxSalary(jobRequestDTO.getMaxSalary());
//        res.setLocation(jobRequestDTO.getLocation());

        Long targetCompanyId = null;
        if(jobRequestDTO.getCompanyId() != null){
            targetCompanyId = jobRequestDTO.getCompanyId();
        }

        if(targetCompanyId != null && !targetCompanyId.equals(res.getCompany().getId())){
            final Long idToSearch = targetCompanyId;
            Company newCompany = companyRepository.findById(idToSearch)
                                                .orElseThrow(() -> new IllegalArgumentException("Target company with ID " + idToSearch + " not found."));

            res.setCompany(newCompany);
        }
        log.info("Successfully updated company by ID and job by ID: {} and {}", companyId, jobId);
        jobRepository.save(res);
        return res;

    }

    public void deleteJobById(Long companyId, Long jobId) {
        log.info("Deleting a company by ID and job by ID: {} and {}", companyId, jobId);
        Job existingJob = jobRepository.findByCompanyIdAndId(companyId, jobId).orElseThrow(() -> new JobNotFoundException("Job not found"));
        jobRepository.delete(existingJob);


    }





}
