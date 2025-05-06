package com.kenzhe.job.service;

import com.kenzhe.job.model.Review;
import com.kenzhe.job.model.dto.ReviewRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    Review createReview(ReviewRequestDTO reviewRequestDTO);

    List<Review> getAllReviews(Long companyId);

    Review getReview(Long companyId, Long reviewId);

    Review updateReview(Long companyId, Long reviewId, ReviewRequestDTO reviewRequestDTO);

    void deleteReviewById(Long companyId, Long reviewId);
}
