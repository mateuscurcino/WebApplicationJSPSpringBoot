package com.code.projectmanagement.service;

import com.code.projectmanagement.dto.ProjectDTO;
import com.code.projectmanagement.model.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectService {

    List<Project> getAllProjects();

    Optional<Project> getProjectById(Long id);

    Project updateProject(Project project);

    Project updateStatusAndRisk(Project project, String status, String risk);

    void deleteProject(Long id);

    Project saveProject(Project project);

    Project fromDTO(ProjectDTO objDto);

    List<String> getStatusProjectCanNotDelete();

    List<String> getAllStatus();

    List<String> getAllRisks();
}