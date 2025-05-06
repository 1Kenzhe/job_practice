package com.kenzhe.job.mapper;


import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.dto.CompanyRequestDTO;
import com.kenzhe.job.model.dto.CompanyResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyMapper {


    @Mapping(target = "jobCount", expression = "java(company.getJobs() != null ? company.getJobs().size() : 0)")
    @Mapping(target = "reviewCount", expression = "java(company.getReviews() != null ? company.getReviews().size() : 0)")
    CompanyResponseDTO toDto(Company company);

    Company toEntity(CompanyRequestDTO companyRequestDTO);

    @Mapping(target = "id", ignore = true) // ID should not be updated from DTO
    @Mapping(target = "jobs", ignore = true) // Collections are not updated here
    @Mapping(target = "reviews", ignore = true) // Collections are not updated here
    void updateEntityFromDto(CompanyRequestDTO updateRequest, @MappingTarget Company company);

}
