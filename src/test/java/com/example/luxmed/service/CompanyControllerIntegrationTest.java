package com.example.luxmed.service;

import com.example.luxmed.dto.CompanyDto;
import com.example.luxmed.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCompanies_ShouldReturnListOfCompanies() throws Exception {
        // Given
        CompanyDto companyDto1 = new CompanyDto();
        companyDto1.setId(1L);
        companyDto1.setName("Company 1");

        CompanyDto companyDto2 = new CompanyDto();
        companyDto2.setId(2L);
        companyDto2.setName("Company 2");

        List<CompanyDto> companies = Arrays.asList(companyDto1, companyDto2);

        when(companyService.getAllCompanies()).thenReturn(companies);

        // When - Then
        mockMvc.perform(get("/api/companies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Company 1")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Company 2")));

        verify(companyService, times(1)).getAllCompanies();
    }

    @Test
    void getCompanyById_WhenCompanyExists_ShouldReturnCompanyDto() throws Exception {
        // Given
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(1L);
        companyDto.setName("Company 1");

        when(companyService.getCompanyById(1L)).thenReturn(companyDto);

        // When - Then
        mockMvc.perform(get("/api/companies/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Company 1")));

        verify(companyService, times(1)).getCompanyById(1L);
    }

    @Test
    void getCompanyById_WhenCompanyDoesNotExist_ShouldReturnNotFound() throws Exception {
        // Given
        when(companyService.getCompanyById(anyLong())).thenThrow(new ResourceNotFoundException("Company not found with id 1"));

        // When - Then
        mockMvc.perform(get("/api/companies/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Company not found with id 1")));

        verify(companyService, times(1)).getCompanyById(1L);
    }

    @Test
    void createCompany_ShouldReturnCreatedCompany() throws Exception {
        // Given
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("New Company");

        CompanyDto savedCompanyDto = new CompanyDto();
        savedCompanyDto.setId(1L);
        savedCompanyDto.setName("New Company");

        when(companyService.createCompany(any(CompanyDto.class))).thenReturn(savedCompanyDto);

        // When - Then
        mockMvc.perform(post("/api/companies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(companyDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("New Company")));

        verify(companyService, times(1)).createCompany(any(CompanyDto.class));
    }

    @Test
    void updateCompany_ShouldReturnUpdatedCompany() throws Exception {
        // Given
        CompanyDto companyDto = new CompanyDto();
        companyDto.setName("Updated Company");

        CompanyDto updatedCompanyDto = new CompanyDto();
        updatedCompanyDto.setId(1L);
        updatedCompanyDto.setName("Updated Company");

        when(companyService.updateCompany(eq(1L), any(CompanyDto.class))).thenReturn(updatedCompanyDto);

        // When - Then
        mockMvc.perform(put("/api/companies/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(companyDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Company")));

        verify(companyService, times(1)).updateCompany(eq(1L), any(CompanyDto.class));
    }

    @Test
    void deleteCompany_ShouldReturnNoContent() throws Exception {
        // Given
        doNothing().when(companyService).deleteCompany(anyLong());

        // When - Then
        mockMvc.perform(delete("/api/companies/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(companyService, times(1)).deleteCompany(1L);
    }

    @Test
    void deleteCompany_WhenCompanyDoesNotExist_ShouldReturnNotFound() throws Exception {
        // Given
        doThrow(new ResourceNotFoundException("Company not found with id 1")).when(companyService).deleteCompany(anyLong());

        // When - Then
        mockMvc.perform(delete("/api/companies/{id}", 1L))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Company not found with id 1")));

        verify(companyService, times(1)).deleteCompany(1L);
    }
}
