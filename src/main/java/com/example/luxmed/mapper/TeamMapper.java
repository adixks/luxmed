package com.example.luxmed.mapper;

import com.example.luxmed.dto.TeamDto;
import com.example.luxmed.entity.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamDto toDto(Team team);
    Team toEntity(TeamDto teamDto);
}
