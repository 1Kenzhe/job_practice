package com.kenzhe.job.service;

import com.kenzhe.job.exception.review.ReviewNotFoundException;
import com.kenzhe.job.mapper.ReviewMapper;
import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.Review;
import com.kenzhe.job.model.dto.ReviewRequestDTO;
import com.kenzhe.job.repository.CompanyRepository;
import com.kenzhe.job.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;
    private final ReviewMapper reviewMapper;

    @Transactional
    public Review createReview(ReviewRequestDTO reviewRequestDTO) {
        log.info("Creating a new company");
        return reviewRepository.save(reviewMapper.toEntity(reviewRequestDTO));
    }

    @Transactional
    public List<Review> getAllReviews(Long companyId){
        log.info("Fetching all reviews");
        return reviewRepository.findByCompanyId(companyId);
    }

    @Transactional
    public Review getReview(Long companyId, Long reviewId){
        log.info("Fetching company by ID and review by ID: {} and {}", companyId, reviewId);
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

    @Transactional
    public Review updateReview(Long companyId, Long reviewId, ReviewRequestDTO reviewRequestDTO){
        Review existingReview = reviewRepository.findByCompanyIdAndId(companyId, reviewId)
                                                    .orElseThrow(() ->  new ReviewNotFoundException(companyId, reviewId));
        log.info("Updating company by ID and review by ID: {} and {}", companyId, reviewId);

        reviewMapper.updateEntityFromDto(reviewRequestDTO, existingReview);
//        existingReview.setTitle(reviewRequestDTO.getTitle());
//        existingReview.setDescription(reviewRequestDTO.getDescription());
//        existingReview.setRating(reviewRequestDTO.getRating());

        Long targetCompanyId = null;
        if(reviewRequestDTO.getCompanyId() != null){
            targetCompanyId = reviewRequestDTO.getCompanyId();
        }

        if(targetCompanyId != null && !targetCompanyId.equals(existingReview.getCompany().getId())){

            final Long idToSearch = targetCompanyId;
            Company newCompany = companyRepository.findById(idToSearch)
                                                    .orElseThrow(() -> new IllegalArgumentException("Target company with ID " + idToSearch + " not found."));

            existingReview.setCompany(newCompany);
        }
        log.info("Successfully updated company by ID and review by ID: {} and {}", companyId, reviewId);
        reviewRepository.save(existingReview);
        return existingReview;

    }

    @Transactional
    public void deleteReviewById(Long companyId, Long reviewId){
        log.info("Deleting a company by ID and job by ID: {} and {}", companyId, reviewId);
        Review existingReview = reviewRepository.findByCompanyIdAndId(companyId, reviewId)
                .orElseThrow(() ->  new ReviewNotFoundException(companyId, reviewId));
        reviewRepository.delete(existingReview);


    }

}
