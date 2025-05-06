package com.kenzhe.job.controller;

import com.kenzhe.job.mapper.CompanyMapper;
import com.kenzhe.job.model.Company;
import com.kenzhe.job.model.dto.CompanyRequestDTO;
import com.kenzhe.job.model.dto.CompanyResponseDTO;
import com.kenzhe.job.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@Tag(name = "Company", description = "Company API")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;



    @PostMapping
    @Operation(summary = "Create a new company")
    @ApiResponse(responseCode = "201", description = "Company created successfully")
    public ResponseEntity<CompanyResponseDTO> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO){

        Company createdCompany = companyService.createCompany(companyRequestDTO);
        CompanyResponseDTO companyResponseDTO = companyMapper.toDto(createdCompany);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdCompany.getId())
                .toUri();

        return ResponseEntity.created(location).body(companyResponseDTO);
    }

    @GetMapping
    @Operation(summary = "Get all companies")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        List<CompanyResponseDTO> companyResponseDTOs = companies.stream()
                        .map(companyMapper::toDto)
                        .toList();
        return new ResponseEntity<>(companyResponseDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a company by ID")
    @ApiResponse(responseCode = "200", description = "Company found")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        CompanyResponseDTO dto = companyMapper.toDto(company);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a company by ID")
    @ApiResponse(responseCode = "200", description = "Company updated successfully")
    public ResponseEntity<CompanyResponseDTO> updateCompanyById(@PathVariable Long id, @Valid @RequestBody CompanyRequestDTO companyRequestDTO){
        Company company= companyService.updateCompanyById(id, companyRequestDTO);
        CompanyResponseDTO dto = companyMapper.toDto(company);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a company by ID")
    @ApiResponse(responseCode = "204", description = "Company deleted successfully")
    public ResponseEntity<Void> deleteCompanyById(@PathVariable Long id){
        companyService.deleteCompanyById(id);
        return ResponseEntity.noContent().build();
    }

}
