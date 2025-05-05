package com.kenzhe.job.controller;

import com.kenzhe.job.model.Review;
import com.kenzhe.job.service.JobService;
import com.kenzhe.job.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
@Tag(name = "Review", description = "Review API")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(Review review){
        return ResponseEntity.ok().body(reviewService.createReview(review));
    }

    @GetMapping("/{companyId}/reviews")
    public ResponseEntity<List<Review>> getAllReviewsByCompanyId(@PathVariable Long companyId){
        return ResponseEntity.ok().body(reviewService.getAllReviews(companyId));
    }

    @GetMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewByReviewIdCompanyId(@PathVariable Long reviewId, Long companyId){
        return new ResponseEntity<>(reviewService.getReview(companyId, reviewId), HttpStatus.OK);
    }

    @PutMapping("/{reviewId}/company/{companyId}")
    public ResponseEntity<Review> updateReviewById(@PathVariable Long reviewId, @PathVariable Long companyId, @RequestBody Review review){
        return new ResponseEntity<>(reviewService.updateReview(reviewId, companyId, review), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}/company/{companyId}")
    public ResponseEntity<Boolean> deleteReviewById(@PathVariable Long reviewId, @PathVariable Long companyId){
        return new ResponseEntity<>(reviewService.deleteReviewById(companyId, reviewId), HttpStatus.OK);
    }

}
