package com.code.projectmanagement.controller;

import com.code.projectmanagement.dto.ProjectDTO;
import com.code.projectmanagement.model.Project;
import com.code.projectmanagement.service.IPersonService;
import com.code.projectmanagement.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Controller
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @Autowired
    private IPersonService personService;

    private static final String REDIRECT = "redirect:/list-projects";

    private static final String PROJECT = "project";

    private static final String MANAGERS = "managers";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    //redirect the root path to the list projects page
    @GetMapping(value = "/")
    public String redirectListProjects(ModelMap model) {
        return REDIRECT;
    }

    @GetMapping(value = "/list-projects")
    public String showProjects(ModelMap model) {
        model.put("projects", projectService.getAllProjects());
        model.put("statusCanNotDelete", projectService.getStatusProjectCanNotDelete());
        return "list-projects";
    }

    @GetMapping(value = "/add-project")
    public String showAddProjectPage(ModelMap model) {
        model.addAttribute(PROJECT, new Project());

        //is used to fill the manager combo
        model.put(MANAGERS, personService.getManagers());
        return PROJECT;
    }

    @GetMapping(value = "/delete-project")
    public String deleteProject(@RequestParam long id) {
        projectService.deleteProject(id);
        return REDIRECT;
    }

    @GetMapping(value = "/update-project")
    public String showUpdateProjectPage(@RequestParam long id, ModelMap model) {
        Optional<Project> optionalProject = projectService.getProjectById(id);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            model.put(PROJECT, project);

            //is used to fill the manager combo
            model.put(MANAGERS, personService.getManagers());

            return PROJECT;
        }

        return REDIRECT;
    }

    @PostMapping(value = "/update-project")
    public String updateProject(ModelMap model, @Valid @ModelAttribute("project") ProjectDTO project, BindingResult result) {

        if (result.hasErrors()) {
            return PROJECT;
        }

        Project objProject = projectService.fromDTO(project);

        projectService.updateProject(objProject);
        return REDIRECT;
    }

    @PostMapping(value = "/add-project")
    public String addProject(ModelMap model, @Valid @ModelAttribute("project") ProjectDTO project, BindingResult result) {

        if (result.hasErrors()) {
            return PROJECT;
        }

        Project objProject = projectService.fromDTO(project);

        projectService.saveProject(objProject);
        return REDIRECT;
    }
}
