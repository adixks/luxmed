package com.example.luxmed.mapper;

import com.example.luxmed.dto.ManagerDto;
import com.example.luxmed.entity.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerDto toDto(Manager manager);
    Manager toEntity(ManagerDto managerDto);
}
