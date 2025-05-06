package com.kenzhe.job.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {

    private Long id;
    private String title;
    private String description;
    private double rating;

    // Include Company information (ID and Name are common choices)
    private Long companyId;
    private String companyName;
}
