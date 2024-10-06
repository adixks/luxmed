package com.example.luxmed.mapper;

import com.example.luxmed.dto.ProjectDto;
import com.example.luxmed.entity.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto toDto(Project project);
    Project toEntity(ProjectDto projectDto);
}
