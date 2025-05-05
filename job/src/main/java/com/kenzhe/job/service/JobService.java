package com.kenzhe.job.service;

import com.kenzhe.job.model.Job;
import com.kenzhe.job.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

    List<Job> getAllJobs(Long companyId);

    Job getJobById(Long companyId, Long jobId);

    Job createJob(Job job);

    Job updateJob(Long companyId, Long jobId, Job job);


    boolean deleteJobById(Long companyId, Long jobId);

}
