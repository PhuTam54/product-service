package com.example.productservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.productservice.dto.DemoGitDTO;
import com.example.productservice.entities.DemoGit;

@Mapper(componentModel = "spring")
public interface DemoGitMapper {
    DemoGitMapper INSTANCE = Mappers.getMapper(DemoGitMapper.class);

    DemoGitDTO demoGitToDemoGitDTO(DemoGit category);

    DemoGit demoGitDTOToDemoGit(DemoGitDTO categoryDTO);
}
