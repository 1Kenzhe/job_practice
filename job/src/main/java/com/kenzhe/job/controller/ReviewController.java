package com.kenzhe.job.controller;

import com.kenzhe.job.mapper.ReviewMapper;
import com.kenzhe.job.model.Review;
import com.kenzhe.job.model.dto.ReviewRequestDTO;
import com.kenzhe.job.model.dto.ReviewResponseDTO;
import com.kenzhe.job.service.JobService;
import com.kenzhe.job.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@Tag(name = "Review", description = "Review API")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping
    public ResponseEntity<ReviewResponseDTO> createReview(@Valid ReviewRequestDTO reviewRequestDTO){
        Review createdReview = reviewService.createReview(reviewRequestDTO);
        ReviewResponseDTO dto = reviewMapper.toDto(createdReview);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/api/companies/{companyId}/reviews/{reviewId}")
                .buildAndExpand(createdReview.getId())
                .toUri();
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{companyId}/reviews")
    public ResponseEntity<List<ReviewResponseDTO>> getAllReviewsByCompanyId(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getAllReviews(companyId);
        List<ReviewResponseDTO> reviewResponseDTOS = reviews.stream()
                                                            .map(reviewMapper::toDto)
                                                            .toList();
        return new ResponseEntity<>(reviewResponseDTOS, HttpStatus.OK);
    }

    @GetMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> getReviewByReviewIdCompanyId(@PathVariable Long reviewId, @PathVariable Long companyId){
        Review review = reviewService.getReview(companyId, reviewId);
        ReviewResponseDTO dto = reviewMapper.toDto(review);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDTO> updateReviewById(@PathVariable Long reviewId, @PathVariable Long companyId, @Valid @RequestBody ReviewRequestDTO reviewRequestDTO){
        Review review = reviewService.updateReview(reviewId, companyId, reviewRequestDTO);
        ReviewResponseDTO dto = reviewMapper.toDto(review);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{companyId}/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReviewById(@PathVariable Long reviewId, @PathVariable Long companyId){
        reviewService.deleteReviewById(companyId, reviewId);
        return ResponseEntity.noContent().build();
    }

}
