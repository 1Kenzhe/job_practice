package com.kenzhe.job.mapper;


import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.dto.CompanyRequestDTO;
import com.kenzhe.job.model.dto.CompanyResponseDTO;
import com.kenzhe.job.repository.CompanyRepository;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyMapper {


    @Mapping(target = "jobCount", expression = "java(company.getJobs() != null ? company.getJobs().size() : 0)")
    @Mapping(target = "reviewCount", expression = "java(company.getReviews() != null ? company.getReviews().size() : 0)")
    CompanyResponseDTO toDto(Company company, @Context CompanyRepository companyRepository);

    @AfterMapping
    default void completeCompanyResponseDTO(Company company, @MappingTarget CompanyResponseDTO dto, @Context CompanyRepository companyRepository) {
        if (company != null && company.getId() != null && dto != null) {
            dto.setJobCount(companyRepository.countJobsByCompanyId(company.getId()));
            dto.setReviewCount(companyRepository.countReviewsByCompanyId(company.getId()));
        } else if (dto != null) {
            dto.setJobCount(0); // Default if company or ID is null
            dto.setReviewCount(0);
        }
    }

    Company toEntity(CompanyRequestDTO companyRequestDTO);

    @Mapping(target = "id", ignore = true) // ID should not be updated from DTO
    @Mapping(target = "jobs", ignore = true) // Collections are not updated here
    @Mapping(target = "reviews", ignore = true) // Collections are not updated here
    void updateEntityFromDto(CompanyRequestDTO updateRequest, @MappingTarget Company company);

}
