package com.kenzhe.job.model.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDTO {


    @NotBlank(message = "Job title cannot be blank")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Job description cannot be blank")
    private String description;

    @NotNull(message = "Rating cannot be null")
    private Double rating;

    @NotNull(message = "Company ID must be provided") // Essential for linking
    private Long companyId;
}
