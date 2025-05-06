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
public class JobRequestDTO {

    @NotBlank(message = "Job title cannot be blank")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "Job description cannot be blank")
    private String description;

    // Consider validation if these should be numeric or follow a pattern
    @Size(max = 100)
    private String minSalary;

    @Size(max = 100)
    private String maxSalary;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 255)
    private String location;

    @NotNull(message = "Company ID must be provided") // Essential for linking
    private Long companyId;
}
