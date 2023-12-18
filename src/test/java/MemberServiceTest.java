import com.code.projectmanagement.model.Member;
import com.code.projectmanagement.model.MemberKey;
import com.code.projectmanagement.model.Person;
import com.code.projectmanagement.model.Project;
import com.code.projectmanagement.repository.MemberRepository;
import com.code.projectmanagement.repository.PersonRepository;
import com.code.projectmanagement.repository.ProjectRepository;
import com.code.projectmanagement.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//Unit Tests of the MemberService Class
class MemberServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MemberRepository memberRepository;

    private MemberService memberService;

    private Project project;

    private Person person;

    private Member member;

    private static final Long ID_PROJECT = 1L;

    private static final Long ID_PERSON = 1L;

    @BeforeEach
    void setUp() {
        projectRepository = Mockito.mock(ProjectRepository.class);
        personRepository = Mockito.mock(PersonRepository.class);
        memberRepository = Mockito.mock(MemberRepository.class);
        memberService = new MemberService(projectRepository, personRepository, memberRepository);

        person = new Person();
        person.setId(ID_PERSON);
        project = new Project();
        project.setId(ID_PROJECT);

        member = new Member(new MemberKey(project, person));
    }

    //method saveMember test - the both parameters are null
    @Test
    void saveMemberTestProjectNullAndPersonNull() {
        Mockito.when(projectRepository.findById(ID_PROJECT)).thenReturn(Optional.empty());
        Mockito.when(personRepository.findByIdAndEmployeeTrue(ID_PERSON)).thenReturn(Optional.empty());

        //when one of the values (or both) is null, registration does not occur
        assertThat(memberService.saveMember(ID_PROJECT, ID_PROJECT)).isNull();
    }

    //method saveMember test - the parameter project is null
    @Test
    void saveMemberTestProjectNullAndPersonNotNull() {
        Mockito.when(projectRepository.findById(ID_PROJECT)).thenReturn(Optional.empty());
        Mockito.when(personRepository.findByIdAndEmployeeTrue(ID_PERSON)).thenReturn(Optional.of(person));

        //when one of the values (or both) is null, registration does not occur
        assertThat(memberService.saveMember(ID_PROJECT, ID_PROJECT)).isNull();
    }

    //method saveMember test - the parameter person is null
    @Test
    void saveMemberTestProjectNotNullAndPersonNull() {
        Mockito.when(projectRepository.findById(ID_PROJECT)).thenReturn(Optional.of(project));
        Mockito.when(personRepository.findByIdAndEmployeeTrue(ID_PERSON)).thenReturn(Optional.empty());

        //when one of the values (or both) is null, registration does not occur
        assertThat(memberService.saveMember(ID_PROJECT, ID_PROJECT)).isNull();
    }

    //method saveMember test - the both parameters are not null
    @Test
    void saveMemberTestProjectNotNullAndPersonNotNull() {
        Mockito.when(projectRepository.findById(ID_PROJECT)).thenReturn(Optional.of(project));
        Mockito.when(personRepository.findByIdAndEmployeeTrue(ID_PERSON)).thenReturn(Optional.of(person));
        Mockito.when(memberRepository.save(Mockito.any())).thenReturn(member);

        //success case
        assertThat(memberService.saveMember(ID_PROJECT, ID_PROJECT)).isNotNull().isEqualTo(member);
    }

}
