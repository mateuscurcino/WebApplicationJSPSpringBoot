import com.code.projectmanagement.dto.ProjectDTO;
import com.code.projectmanagement.model.Person;
import com.code.projectmanagement.model.Project;
import com.code.projectmanagement.model.enums.Risk;
import com.code.projectmanagement.model.enums.Status;
import com.code.projectmanagement.repository.PersonRepository;
import com.code.projectmanagement.repository.ProjectRepository;
import com.code.projectmanagement.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//Unit Tests of the ProjectService Class
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private PersonRepository personRepository;

    private ProjectService projectService;

    private List<Project> projects;

    private Project project;

    private Person manager;

    private ProjectDTO projectDTO;

    private static final Long ID_PROJECT = 1L;

    private static final String STATUS_INVALID = "STATUS INVALID";
    private static final String RISK_INVALID = "RISK INVALID";

    @BeforeEach
    void setUp() {
        projectRepository = Mockito.mock(ProjectRepository.class);
        personRepository = Mockito.mock(PersonRepository.class);

        projectService = new ProjectService(projectRepository, personRepository);

        manager = new Person();
        manager.setId(1L);

        project = new Project();
        project.setId(1L);
        project.setManager(manager);

        projects = new ArrayList<>();
        projects.add(project);

        projectDTO = new ProjectDTO();
        projectDTO.setId(ID_PROJECT);
        projectDTO.setManager(manager.getId());
    }

    //method deleteProject test - project status allows
    @Test
    void deleteProjectTestStatusCanDelete() {

        //status PLANNED can be removed
        project.setStatus(Status.PLANNED.getDescription());
        Mockito.when(projectRepository.findById(Mockito.any())).thenReturn(Optional.of(project));

        projectService.deleteProject(ID_PROJECT);

        Mockito.verify(projectRepository, Mockito.times(1)).delete(Mockito.any());
        //call method projectRepository.delete
    }

    //method deleteProject test - project status can not delete
    @Test
    void deleteProjectTestStatusCanNotDelete() {

        //status STARTED can not be removed
        project.setStatus(Status.STARTED.getDescription());
        Mockito.when(projectRepository.findById(Mockito.any())).thenReturn(Optional.of(project));

        projectService.deleteProject(ID_PROJECT);

        Mockito.verify(projectRepository, Mockito.times(0)).delete(Mockito.any());
        //doesn't call method projectRepository.delete
    }

    //method updateStatusAndRisk test - status invalid and risk invalid
    @Test
    void updateStatusAndRiskTestStatusInvalidAndRiskInvalid() {
        Project result = projectService.updateStatusAndRisk(project, STATUS_INVALID, RISK_INVALID);

        assertThat(result).isNull();
    }

    //method updateStatusAndRisk test - status not valid and risk not valid
    @Test
    void updateStatusAndRiskTestStatusValidAndRiskInvalid() {
        Project result = projectService.updateStatusAndRisk(project, Status.PLANNED.getDescription(), RISK_INVALID);

        assertThat(result).isNull();
    }

    //method updateStatusAndRisk test - status invalid and risk valid
    @Test
    void updateStatusAndRiskTestStatusInvalidAndRiskValid() {
        Project result = projectService.updateStatusAndRisk(project, STATUS_INVALID, Risk.HIGH.getDescription());

        assertThat(result).isNull();
    }

    //method updateStatusAndRisk test - status null and risk not valid
    @Test
    void updateStatusAndRiskTestStatusNullAndRiskInvalid() {
        Project result = projectService.updateStatusAndRisk(project, null, RISK_INVALID);

        assertThat(result).isNull();
    }

    //method updateStatusAndRisk test - status invalid and risk null
    @Test
    void updateStatusAndRiskTestStatusInvalidAndRiskNull() {
        Project result = projectService.updateStatusAndRisk(project, STATUS_INVALID, null);

        assertThat(result).isNull();
    }

    //method updateStatusAndRisk test - status null and risk null
    @Test
    void updateStatusAndRiskTestStatusNullAndRiskNull() {
        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(project);

        Project result = projectService.updateStatusAndRisk(project, null, null);

        assertThat(result).isNotNull().isEqualTo(project);
    }

    //method updateStatusAndRisk test - status null and risk valid
    @Test
    void updateStatusAndRiskTestStatusNullAndRiskValid() {
        project.setRisk(Risk.HIGH.getDescription());
        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(project);

        Project result = projectService.updateStatusAndRisk(project, null, Risk.HIGH.getDescription());

        assertThat(result).isNotNull().isEqualTo(project);
    }

    //method updateStatusAndRisk test - status valid and risk null
    @Test
    void updateStatusAndRiskTestStatusValidAndRiskNull() {
        project.setStatus(Status.PLANNED.getDescription());
        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(project);

        Project result = projectService.updateStatusAndRisk(project, Status.PLANNED.getDescription(), null);

        assertThat(result).isNotNull().isEqualTo(project);
    }

    //method updateStatusAndRisk test - status valid and risk valid
    @Test
    void updateStatusAndRiskTestStatusValidAndRiskValid() {
        project.setStatus(Status.PLANNED.getDescription());
        project.setRisk(Risk.HIGH.getDescription());
        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(project);

        Project result = projectService.updateStatusAndRisk(project, Status.PLANNED.getDescription(), Risk.HIGH.getDescription());

        assertThat(result).isNotNull().isEqualTo(project);
    }

    //method getAllProjects test
    @Test
    void getAllProjectsTest() {
        Mockito.when(projectRepository.findAll()).thenReturn(projects);

        List<Project> projects = projectService.getAllProjects();

        assertThat(projects).isNotNull().hasSize(1);
    }

    //method saveProject test
    @Test
    void saveProjectTest() {
        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(project);

        assertThat(projectService.saveProject(project)).isNotNull().isEqualTo(project);
    }

    //method getProjectById test
    @Test
    void getProjectByIdTest() {
        Mockito.when(projectRepository.findById(Mockito.any())).thenReturn(Optional.of(project));

        Optional<Project> optionalProject = projectService.getProjectById(ID_PROJECT);

        if (optionalProject.isPresent()) {
            Project projObj = optionalProject.get();

            assertThat(projObj).isNotNull();

            assertThat(projObj.getId()).isEqualTo(ID_PROJECT);
        }
    }

    //method updateProject test
    @Test
    void updateProjectTest() {
        Mockito.when(projectRepository.save(Mockito.any())).thenReturn(project);

        assertThat(projectService.updateProject(project)).isNotNull().isEqualTo(project);
    }

    //method getStatusProjectCanNotDelete test
    @Test
    void getStatusProjectCanNotDeleteTest() {
        List<String> status = projectService.getStatusProjectCanNotDelete();

        assertThat(status).isNotNull().hasSize(3);
    }

    //method getAllStatus test
    @Test
    void getAllStatusTest() {
        List<String> status = projectService.getAllStatus();

        assertThat(status).isNotNull().hasSize(Status.values().length);
    }

    //method getAllRisksTest test
    @Test
    void getAllRisksTest() {
        List<String> risks = projectService.getAllRisks();

        assertThat(risks).isNotNull().hasSize(Risk.values().length);
    }

    //method fromDTO test - new register
    @Test
    void fromDTOTestNewRegister() {
        Mockito.when(personRepository.findByIdAndManagerTrue(Mockito.any())).thenReturn(Optional.of(manager));

        projectDTO.setId(null);

        Project result = projectService.fromDTO(projectDTO);

        assertThat(result.getId()).isNull();
    }

    //method fromDTO test - existing register
    @Test
    void fromDTOTestExistingRegister() {
        Mockito.when(personRepository.findByIdAndManagerTrue(Mockito.any())).thenReturn(Optional.of(manager));
        Mockito.when(projectRepository.findById(Mockito.any())).thenReturn(Optional.of(project));

        Project result = projectService.fromDTO(projectDTO);

        assertThat(result.getId()).isNotNull().isEqualTo(project.getId());
    }

}
