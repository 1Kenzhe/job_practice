package com.kenzhe.job.service;

import com.kenzhe.job.exception.review.ReviewNotFoundException;
import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.Review;
import com.kenzhe.job.repository.CompanyRepository;
import com.kenzhe.job.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(Long companyId){
        return reviewRepository.findByCompanyId(companyId);
    }

    public Review getReview(Long companyId, Long reviewId){
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(() -> new ReviewNotFoundException(companyId, reviewId));

        //  Alternative implementation using a for loop
        //        for(Review review : reviews){
        //            if (review.getId().equals(reviewId)) {
        //                return review;
        //            }
        //        }
        //        return null;
    }

    public Review updateReview(Long companyId, Long reviewId, Review updateReview){
        Review existingReview = reviewRepository.findByCompanyIdAndId(companyId, reviewId)
                                                    .orElseThrow(() ->  new ReviewNotFoundException(companyId, reviewId));

        existingReview.setTitle(updateReview.getTitle());
        existingReview.setDescription(updateReview.getDescription());
        existingReview.setRating(updateReview.getRating());

        Long targetCompanyId = null;
        if(updateReview.getCompany() != null){
            targetCompanyId = updateReview.getCompany().getId();
        }

        if(targetCompanyId != null && !targetCompanyId.equals(existingReview.getCompany().getId())){

            final Long idToSearch = targetCompanyId;
            Company newCompany = companyRepository.findById(idToSearch)
                                                    .orElseThrow(() -> new IllegalArgumentException("Target company with ID " + idToSearch + " not found."));

            existingReview.setCompany(newCompany);
        }
        reviewRepository.save(existingReview);
        return existingReview;

    }

    public boolean deleteReviewById(Long companyId, Long reviewId){
        try{
            Review existingReview = reviewRepository.findByCompanyIdAndId(companyId, reviewId)
                    .orElseThrow(() ->  new ReviewNotFoundException(companyId, reviewId));
            reviewRepository.deleteById(reviewId);
            return true;
        } catch(Exception e){
            return false;
        }
    }

}
