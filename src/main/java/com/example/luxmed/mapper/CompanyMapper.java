package com.example.luxmed.mapper;

import com.example.luxmed.dto.*;
import com.example.luxmed.entity.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface CompanyMapper {
    CompanyDto toDto(Company company);
    @InheritInverseConfiguration
    Company toEntity(CompanyDto companyDto);
}
