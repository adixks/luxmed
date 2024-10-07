package com.example.luxmed.mapper;

import com.example.luxmed.dto.TeamDto;
import com.example.luxmed.entity.Team;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProjectMapper.class})
public interface TeamMapper {
    TeamDto toDto(Team team);

    @InheritInverseConfiguration
    Team toEntity(TeamDto teamDto);
}
