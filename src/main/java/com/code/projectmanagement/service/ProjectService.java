package com.code.projectmanagement.service;

import com.code.projectmanagement.dto.ProjectDTO;
import com.code.projectmanagement.model.Person;
import com.code.projectmanagement.model.Project;
import com.code.projectmanagement.model.enums.Risk;
import com.code.projectmanagement.model.enums.Status;
import com.code.projectmanagement.repository.PersonRepository;
import com.code.projectmanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements IProjectService {

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, PersonRepository personRepository) {
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project updateStatusAndRisk(Project project, String status, String risk) {
        if (risk != null) {
            String newRisk = risk.trim().toUpperCase();

            //checks if it is a valid risk value
            if (!this.getAllRisks().contains(newRisk))
                return null;

            project.setRisk(newRisk);
        }

        if (status != null) {
            String newStatus = status.trim().toUpperCase();

            //checks if it is a valid status value
            if (!this.getAllStatus().contains(newStatus))
                return null;

            project.setStatus(newStatus);
        }

        return this.updateProject(project);
    }


    @Override
    public void deleteProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isPresent()) {
            Project projectObj = project.get();

            //checks the project status allows removal of the record
            if (projectObj.getStatus() != null && !this.getStatusProjectCanNotDelete().contains(projectObj.getStatus().toUpperCase()))
                projectRepository.delete(projectObj);
        }
    }

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project fromDTO(ProjectDTO objDto) {

        //the person must be in the category manager
        Optional<Person> person = personRepository.findByIdAndManagerTrue(objDto.getManager());

        //new register
        if (objDto.getId() == null) {
            return new Project(objDto.getId(), objDto.getName(), objDto.getStartDate(), objDto.getExpectedEndDate(), objDto.getEndDate(), objDto.getDescription(),
                    objDto.getBudget(), person.isPresent() ? person.get() : null);
        }

        //existing registration
        Optional<Project> project = projectRepository.findById(objDto.getId());

        if (project.isPresent()) {
            Project objProj = project.get();

            //maintains current status and risk
            return new Project(objDto.getId(), objDto.getName(), objDto.getStartDate(), objDto.getExpectedEndDate(), objDto.getEndDate(), objDto.getDescription(),
                    objProj.getStatus(), objDto.getBudget(), objProj.getRisk(), person.isPresent() ? person.get() : null);
        }

        return null;
    }

    @Override
    public List<String> getStatusProjectCanNotDelete() {
        List<String> status = new ArrayList<>();

        for (Status s : Status.values()) {
            if (!s.isCanDeleteProject())
                status.add(s.getDescription());
        }

        return status;
    }

    public List<String> getAllStatus() {
        List<String> status = new ArrayList<>();

        for (Status s : Status.values()) {
            status.add(s.getDescription());
        }

        return status;
    }

    public List<String> getAllRisks() {
        List<String> risks = new ArrayList<>();

        for (Risk r : Risk.values()) {
            risks.add(r.getDescription());
        }

        return risks;
    }
}