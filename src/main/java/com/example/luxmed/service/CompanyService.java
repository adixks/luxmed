package com.example.luxmed.service;

import com.example.luxmed.dto.CompanyDto;
import com.example.luxmed.entity.Company;
import com.example.luxmed.exception.ResourceNotFoundException;
import com.example.luxmed.mapper.CompanyMapper;
import com.example.luxmed.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CompanyService {
    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;

    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toDto)
                .collect(Collectors.toList());
    }

    public CompanyDto getCompanyById(Long id) {
        return companyRepository.findById(id)
                .map(companyMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
    }

    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        return companyMapper.toDto(companyRepository.save(company));
    }

    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        return companyRepository.findById(id)
                .map(existingCompany -> {
                    existingCompany.setName(companyDto.getName());
                    existingCompany.setDepartments(companyMapper.toEntity(companyDto).getDepartments());
                    return companyMapper.toDto(companyRepository.save(existingCompany));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with id " + id));
    }

    public void deleteCompany(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Company not found with id " + id);
        }
        companyRepository.deleteById(id);
    }
}
