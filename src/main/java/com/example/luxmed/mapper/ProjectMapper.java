package com.example.luxmed.mapper;

import com.example.luxmed.dto.ProjectDto;
import com.example.luxmed.entity.Project;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ManagerMapper.class})
public interface ProjectMapper {
    ProjectDto toDto(Project project);

    @InheritInverseConfiguration
    Project toEntity(ProjectDto projectDto);
}
