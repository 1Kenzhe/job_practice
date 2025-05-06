package com.kenzhe.job.mapper;

import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.Job;
import com.kenzhe.job.model.dto.CompanyRequestDTO;
import com.kenzhe.job.model.dto.JobRequestDTO;
import com.kenzhe.job.model.dto.JobResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface JobMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    JobResponseDTO toDto(Job job);

    @Mapping(target = "company", ignore = true)
    Job toEntity(JobRequestDTO jobRequestDTO);

    @Mapping(target = "company", ignore = true)
    @Mapping(target = "id", ignore = true) // ID should not be updated from DTO
    void updateEntityFromDto(JobRequestDTO jobRequestDTO, @MappingTarget Job job);
}
