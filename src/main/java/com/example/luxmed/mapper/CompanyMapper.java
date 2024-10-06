package com.example.luxmed.mapper;

import com.example.luxmed.dto.CompanyDto;
import com.example.luxmed.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto toDto(Company company);
    Company toEntity(CompanyDto companyDto);
}
