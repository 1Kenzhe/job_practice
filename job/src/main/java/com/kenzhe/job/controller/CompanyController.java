package com.kenzhe.job.controller;

import com.kenzhe.job.model.Company;
import com.kenzhe.job.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@Tag(name = "Company", description = "Company API")
public class CompanyController {

    public final CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(@RequestBody Company company){
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Company> updateCompanyById(@PathVariable Long id, @RequestBody Company company){
        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Company> deleteCompanyById(@PathVariable Long id){
        return ResponseEntity.ok().body(null);
    }

}
