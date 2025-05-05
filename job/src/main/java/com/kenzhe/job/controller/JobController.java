package com.kenzhe.job.controller;


import com.kenzhe.job.model.Job;
import com.kenzhe.job.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/api/jobs")
@RestController
@Tag(name = "Job", description = "Job API")
public class JobController {

    private final JobService jobService;


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
    @PostMapping("/create")
    public ResponseEntity<Job> createJob(@RequestBody Job job){
        return new ResponseEntity<>(jobService.createJob(job), HttpStatus.CREATED);
    }

    @Operation(
            description = "Searching for all jobs",
            summary = "This is a summary for GET all jobs endpoint"
    )
    @GetMapping("/{companyId}/jobs")
    public ResponseEntity<List<Job>> getAllJobsByCompanyId(@PathVariable Long companyId){
        return new ResponseEntity<>(jobService.getAllJobs(companyId), HttpStatus.FOUND);
    }

    @Operation(
            description = "Searching for a Job by Company ID",
            summary = "This is a summary for another GET job endpoint"
    )
    @GetMapping("/{companyId}/jobs/{jobId}")
    public ResponseEntity<Job> getJobById(@Parameter(description = "ID of the user to retrieve", required = true)
                                          @PathVariable Long jobId,
                                          @PathVariable Long companyId){
        return new ResponseEntity<>(jobService.getJobById(companyId, jobId), HttpStatus.FOUND);
    }

    @Operation(
            description = "Updating a job by ID",
            summary = "This is a summary for PUT job endpoint"
    )
    @PutMapping("/{jobId}/company/{companyId}")
    public ResponseEntity<Job> updateJob(@PathVariable Long jobId, @PathVariable Long companyId, @RequestBody Job job){
        return new ResponseEntity<>(jobService.updateJob(companyId, jobId, job), HttpStatus.OK);
    }

    @Operation(
            description = "Deleting a job by ID",
            summary = "This is a summary for DELETE job endpoint"
    )
    @DeleteMapping("/{jobId}/company/{companyId}")
    public ResponseEntity<Boolean> deleteJob(@PathVariable Long jobId, @PathVariable Long companyId){
        return new ResponseEntity<>(jobService.deleteJobById(companyId, jobId), HttpStatus.OK);
    }

}
