package com.kenzhe.job.mapper;


import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.Review;
import com.kenzhe.job.model.dto.CompanyRequestDTO;
import com.kenzhe.job.model.dto.ReviewRequestDTO;
import com.kenzhe.job.model.dto.ReviewResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ReviewMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    ReviewResponseDTO toDto(Review review);

    @Mapping(target = "company", ignore = true)
    Review toEntity(ReviewRequestDTO reviewRequestDTO);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "id", ignore = true) // ID should not be updated from DTO
    void updateEntityFromDto(ReviewRequestDTO reviewRequestDTO, @MappingTarget Review review);

}
