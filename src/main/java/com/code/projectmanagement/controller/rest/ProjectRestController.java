package com.code.projectmanagement.controller.rest;

import com.code.projectmanagement.dto.ProjectRestDTO;
import com.code.projectmanagement.model.Project;
import com.code.projectmanagement.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

//Controller that provides a web service to change project risk and status

@RestController
@RequestMapping(value = "/projects")
public class ProjectRestController {

    @Autowired
    private IProjectService projectService;

    @PutMapping(value = "/{id}")
    public ResponseEntity<Project> update(@Valid @RequestBody ProjectRestDTO projDtoObj, @PathVariable Long id) {

        Optional<Project> project = projectService.getProjectById(id);

        if (!project.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Project projectUpdated = projectService.updateStatusAndRisk(project.get(), projDtoObj.getStatus(), projDtoObj.getRisk());

        //risk or status not valid
        if(projectUpdated == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body(projectUpdated);
    }
}
