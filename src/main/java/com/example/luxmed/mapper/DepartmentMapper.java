package com.example.luxmed.mapper;

import com.example.luxmed.dto.DepartmentDto;
import com.example.luxmed.entity.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
    Department toEntity(DepartmentDto departmentDto);
}
