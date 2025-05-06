package com.kenzhe.job.service;

import com.kenzhe.job.model.Job;
import com.kenzhe.job.model.Review;
import com.kenzhe.job.model.dto.JobRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

    List<Job> getAllJobs(Long companyId);

    Job getJobById(Long companyId, Long jobId);

    Job createJob(JobRequestDTO jobRequestDTO);

    Job updateJob(Long companyId, Long jobId, JobRequestDTO jobRequestDTO);


    void deleteJobById(Long companyId, Long jobId);

}
