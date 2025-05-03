package com.kenzhe.job.exception.review;

public class JobNotFoundException extends ResourceNotFoundException{
    public JobNotFoundException(Long companyId, Long jobId) {
        super("Job not found with ID: " + jobId + " for Company ID: " + companyId); // Specific message
    }

    // Constructor if lookup is only by job ID (if that scenario exists)
    public JobNotFoundException(Long jobId) {
        super("Job not found with ID: " + jobId);
    }

    // Optional: Add a constructor allowing a custom message if ever needed
    public JobNotFoundException(String message) {
        super(message);
    }
}
