package com.kenzhe.job.exception.review;

public class CompanyNotFoundException extends ResourceNotFoundException{
    public CompanyNotFoundException(Long companyId) {
        super("Company not found with ID: " + companyId);
    }

    public CompanyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
