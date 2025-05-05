package com.kenzhe.job.service;

import com.kenzhe.job.exception.review.JobNotFoundException;
import com.kenzhe.job.exception.review.ReviewNotFoundException;
import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.Job;
import com.kenzhe.job.model.Review;
import com.kenzhe.job.repository.CompanyRepository;
import com.kenzhe.job.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;

    public List<Job> getAllJobs(Long companyId) {
        return jobRepository.findByCompanyId(companyId);
    }

    public Job getJobById(Long companyId, Long jobId) {
        List<Job> jobs = jobRepository.findByCompanyId(companyId);
        return jobs.stream()
            .filter(job -> job.getId().equals(jobId))
            .findFirst()
            .orElseThrow(() -> new JobNotFoundException(companyId, jobId));
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Job updateJob(Long companyId, Long jobId, Job updateJob) {
        Job res = jobRepository.findByCompanyIdAndId(companyId, jobId)
                                .orElseThrow(() -> new JobNotFoundException(companyId, jobId));


        res.setTitle(updateJob.getTitle());
        res.setDescription(updateJob.getDescription());
        res.setMinSalary(updateJob.getMinSalary());
        res.setMaxSalary(updateJob.getMaxSalary());
        res.setLocation(updateJob.getLocation());

        Long targetCompanyId = null;
        if(updateJob.getCompany() != null){
            targetCompanyId = updateJob.getCompany().getId();
        }

        if(targetCompanyId != null && !targetCompanyId.equals(res.getCompany().getId())){
            final Long idToSearch = targetCompanyId;
            Company newCompany = companyRepository.findById(idToSearch)
                                                .orElseThrow(() -> new IllegalArgumentException("Target company with ID " + idToSearch + " not found."));

            res.setCompany(newCompany);
        }
        jobRepository.save(res);
        return res;

    }

    public boolean deleteJobById(Long companyId, Long jobId) {
        try{
            Job existingJob = jobRepository.findByCompanyIdAndId(companyId, jobId).orElseThrow(() -> new JobNotFoundException("Job not found"));
            jobRepository.deleteById(jobId);
            return true;
        }catch(Exception e){
            return false;
        }

    }





}
