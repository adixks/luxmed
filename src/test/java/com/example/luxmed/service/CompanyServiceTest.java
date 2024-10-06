package com.example.luxmed.service;

import com.example.luxmed.dto.CompanyDto;
import com.example.luxmed.entity.Company;
import com.example.luxmed.exception.ResourceNotFoundException;
import com.example.luxmed.mapper.CompanyMapper;
import com.example.luxmed.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void getAllCompanies_ShouldReturnListOfCompanyDtos() {
        // Given
        Company company1 = new Company();
        company1.setId(1L);
        company1.setName("Company 1");

        Company company2 = new Company();
        company2.setId(2L);
        company2.setName("Company 2");

        CompanyDto companyDto1 = new CompanyDto();
        companyDto1.setId(1L);
        companyDto1.setName("Company 1");

        CompanyDto companyDto2 = new CompanyDto();
        companyDto2.setId(2L);
        companyDto2.setName("Company 2");

        when(companyRepository.findAll()).thenReturn(Arrays.asList(company1, company2));
        when(companyMapper.toDto(company1)).thenReturn(companyDto1);
        when(companyMapper.toDto(company2)).thenReturn(companyDto2);

        // When
        List<CompanyDto> result = companyService.getAllCompanies();

        // Then
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Company 1", result.get(0).getName());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Company 2", result.get(1).getName());
    }

    @Test
    void getCompanyById_WhenCompanyExists_ShouldReturnCompanyDto() {
        // Given
        Company company = new Company();
        company.setId(1L);
        company.setName("Company 1");

        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(1L);
        companyDto.setName("Company 1");

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyMapper.toDto(company)).thenReturn(companyDto);

        // When
        CompanyDto result = companyService.getCompanyById(1L);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("Company 1", result.getName());
    }

    @Test
    void getCompanyById_WhenCompanyDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Given
        when(companyRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When - Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> companyService.getCompanyById(1L));
        assertEquals("Company not found with id 1", exception.getMessage());
    }

    @Test
    void createCompany_ShouldSaveAndReturnCompanyDto() {
        // Given
        Company company = new Company();
        company.setName("New Company");

        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("New Company");

        Company savedCompany = new Company();
        savedCompany.setId(1L);
        savedCompany.setName("New Company");

        CompanyDto savedCompanyDto = new CompanyDto();
        savedCompanyDto.setId(1L);
        savedCompanyDto.setName("New Company");

        when(companyMapper.toEntity(companyDto)).thenReturn(company);
        when(companyRepository.save(company)).thenReturn(savedCompany);
        when(companyMapper.toDto(savedCompany)).thenReturn(savedCompanyDto);

        // When
        CompanyDto result = companyService.createCompany(companyDto);

        // Then
        assertEquals(1L, result.getId());
        assertEquals("New Company", result.getName());
    }

    @Test
    void updateCompany_WhenCompanyExists_ShouldUpdateAndReturnCompanyDto() {
        // Given
        Company existingCompany = new Company();
        existingCompany.setId(1L);
        existingCompany.setName("Old Company");

        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Updated Company");

        Company updatedCompany = new Company();
        updatedCompany.setId(1L);
        updatedCompany.setName("Updated Company");

        when(companyRepository.findById(1L)).thenReturn(Optional.of(existingCompany));
        when(companyMapper.toEntity(companyDto)).thenReturn(updatedCompany);
        when(companyRepository.save(existingCompany)).thenReturn(existingCompany);
        when(companyMapper.toDto(existingCompany)).thenReturn(companyDto);

        // When
        CompanyDto result = companyService.updateCompany(1L, companyDto);

        // Then
        assertEquals("Updated Company", result.getName());
    }

    @Test
    void deleteCompany_WhenCompanyExists_ShouldDeleteCompany() {
        // Given
        when(companyRepository.existsById(1L)).thenReturn(true);

        // When
        companyService.deleteCompany(1L);

        // Then
        verify(companyRepository, times(1)).existsById(1L);
        verify(companyRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCompany_WhenCompanyDoesNotExist_ShouldThrowResourceNotFoundException() {
        // Given
        when(companyRepository.existsById(anyLong())).thenReturn(false);

        // When - Then
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> companyService.deleteCompany(1L));
        assertEquals("Company not found with id 1", exception.getMessage());

        verify(companyRepository, times(1)).existsById(1L);
        verify(companyRepository, never()).deleteById(anyLong());
    }
}
