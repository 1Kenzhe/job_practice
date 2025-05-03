package com.kenzhe.job.service;

import com.kenzhe.job.exception.review.JobNotFoundException;
import com.kenzhe.job.exception.review.ReviewNotFoundException;
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

    public List<Job> getAllJobs() {
        List<Job> res =jobRepository.findAll();
        return res;
    }

    public Job getJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }

    public Job createJob(Job job) {
        Job res = jobRepository.save(job);
        return res;
    }

    public Job updateJob(Long id, Job job) {
        Job res = getJobById(id);
        if(res != null) {
            res.setTitle(job.getTitle());
            res.setDescription(job.getDescription());
            res.setMinSalary(job.getMinSalary());
            res.setMaxSalary(job.getMaxSalary());
            res.setLocation(job.getLocation());
            jobRepository.save(res);
        }
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
