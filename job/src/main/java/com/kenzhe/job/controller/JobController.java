package com.kenzhe.job.controller;


import com.kenzhe.job.mapper.JobMapper;
import com.kenzhe.job.model.Job;
import com.kenzhe.job.model.dto.JobRequestDTO;
import com.kenzhe.job.model.dto.JobResponseDTO;
import com.kenzhe.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/companies")
@RestController
@Tag(name = "Job", description = "Job API")
public class JobController {

    private final JobService jobService;
    private final JobMapper jobMapper;

    @Operation(
        description = "Create a new job",
            summary = "This is a summary for create job endpoint",
            responses = {
                @ApiResponse(responseCode = "200", description = "Job created successfully"),
                @ApiResponse(responseCode = "400", description = "Bad request"),
                @ApiResponse(responseCode = "404", description = "Job not found"),
                @ApiResponse(responseCode = "500", description = "Internal server error")

            }

    )
    @PostMapping("/jobs")
    public ResponseEntity<JobResponseDTO> createJob(@Valid @RequestBody JobRequestDTO jobRequestDTO){
        Job createdJob = jobService.createJob(jobRequestDTO);
        JobResponseDTO jobResponseDTO = jobMapper.toDto(createdJob);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{companyId}/jobs/{jobId}")
                .buildAndExpand(jobRequestDTO.getCompanyId(), createdJob.getId())
                .toUri();

        return new ResponseEntity<>(jobResponseDTO, HttpStatus.CREATED);
    }

    @Operation(
            description = "Searching for all jobs",
            summary = "This is a summary for GET all jobs endpoint"
    )
    @GetMapping("/{companyId}/jobs")
    public ResponseEntity<List<JobResponseDTO>> getAllJobsByCompanyId(@PathVariable Long companyId){
        List<Job> jobs = jobService.getAllJobs(companyId);
        List<JobResponseDTO> dtos = jobs.stream()
                                        .map(jobMapper::toDto)
                                        .toList();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Operation(
            description = "Searching for a Job by Company ID",
            summary = "This is a summary for another GET job endpoint"
    )
    @GetMapping("/{companyId}/jobs/{jobId}")
    public ResponseEntity<JobResponseDTO> getJobById(@Parameter(description = "ID of the user to retrieve", required = true)
                                          @PathVariable Long jobId,
                                        @PathVariable Long companyId){
        Job job = jobService.getJobById(companyId, jobId);
        JobResponseDTO dto = jobMapper.toDto(job);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(
            description = "Updating a job by ID",
            summary = "This is a summary for PUT job endpoint"
    )
    @PutMapping("/{companyId}/jobs/{jobId}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable Long jobId, @PathVariable Long companyId, @RequestBody JobRequestDTO jobRequestDTO){
        Job updatedJob = jobService.updateJob(companyId, jobId, jobRequestDTO);
        JobResponseDTO dto = jobMapper.toDto(updatedJob);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(
            description = "Deleting a job by ID",
            summary = "This is a summary for DELETE job endpoint"
    )
    @DeleteMapping("/{companyId}/jobs/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long jobId, @PathVariable Long companyId){
        jobService.deleteJobById(companyId, jobId);
        return ResponseEntity.noContent().build();
    }

}
