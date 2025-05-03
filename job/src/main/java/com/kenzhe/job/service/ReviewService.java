package com.kenzhe.job.service;

import com.kenzhe.job.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    Review createReview(Review review);

    List<Review> getAllReviews(Long companyId);

    Review getReview(Long companyId, Long reviewId);

    Review updateReview(Long companyId, Long reviewId, Review review);
}
