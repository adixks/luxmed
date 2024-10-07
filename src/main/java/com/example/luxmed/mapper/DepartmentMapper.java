package com.example.luxmed.mapper;

import com.example.luxmed.dto.DepartmentDto;
import com.example.luxmed.entity.Department;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);

    @InheritInverseConfiguration
    Department toEntity(DepartmentDto departmentDto);
}
