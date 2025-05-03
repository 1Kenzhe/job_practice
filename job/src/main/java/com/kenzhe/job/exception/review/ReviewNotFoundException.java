package com.kenzhe.job.exception.review;

public class ReviewNotFoundException extends ResourceNotFoundException{

    public ReviewNotFoundException(Long companyId, Long reviewId) {
        super("Review not found with ID: " + reviewId + " for Company ID: " + companyId); // Specific message
    }

    // Constructor if lookup is only by review ID (if that scenario exists)
    public ReviewNotFoundException(Long reviewId) {
        super("Review not found with ID: " + reviewId);
    }

    // Optional: Add a constructor allowing a custom message if ever needed
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
