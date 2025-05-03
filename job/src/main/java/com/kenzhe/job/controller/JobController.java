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
        return ResponseEntity.ok().body(jobService.createJob(job));
    }

    @Operation(
            description = "Searching for all jobs",
            summary = "This is a summary for GET all jobs endpoint"
    )
    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobs(){
        List<Job> jobs = jobService.getAllJobs();
        if(jobs.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body( jobService.getAllJobs());
    }

    @Operation(
            description = "Searching for a job by its ID",
            summary = "This is a summary for GET job endpoint"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@Parameter(description = "ID of the user to retrieve", required = true)
                                          @PathVariable Long id){

        Job job = jobService.getJobById(id);
        if(job == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(jobService.getJobById(id));
    }


    @Operation(
            description = "Searching for a Job by Company ID",
            summary = "This is a summary for another GET job endpoint"
    )
    @GetMapping("{id}/company/{companyId}")
    public ResponseEntity<Job> getJobByCompanyId(@Parameter(description = "ID of the user to retrieve", required = true)
                                                 @PathVariable Long id,
                                                 @PathVariable Long companyId){

       // get company by job id
        return ResponseEntity.ok().body(null);
    }

    @Operation(
            description = "Updating a job by ID",
            summary = "This is a summary for PUT job endpoint"
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job){
        return ResponseEntity.ok().body(jobService.updateJob(id, job));
    }

    @Operation(
            description = "Deleting a job by ID",
            summary = "This is a summary for DELETE job endpoint"
    )
    @DeleteMapping("/{jobId}/company/{companyId}")
    public ResponseEntity<Boolean> deleteJob(@PathVariable Long jobId, @PathVariable Long companyId){
        return ResponseEntity.ok().body(jobService.deleteJobById(companyId, jobId));
    }

}
