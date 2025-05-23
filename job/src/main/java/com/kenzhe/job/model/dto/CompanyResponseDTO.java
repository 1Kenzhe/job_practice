package com.kenzhe.job.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {

    private Long id;

    private String name;

    private String description;

    private int jobCount;

    private int reviewCount;

}
