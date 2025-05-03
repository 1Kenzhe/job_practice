package com.kenzhe.job.service;

import com.kenzhe.job.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

    List<Job> getAllJobs();

    Job getJobById(Long id);

    Job createJob(Job job);

    Job updateJob(Long id, Job job);

    boolean deleteJobById(Long companyId, Long jobId);

}
