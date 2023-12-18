import com.code.projectmanagement.dto.PersonDTO;
import com.code.projectmanagement.model.Person;
import com.code.projectmanagement.model.enums.Position;
import com.code.projectmanagement.repository.PersonRepository;
import com.code.projectmanagement.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//Unit Tests of the PersonService Class
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    private List<Person> managers;

    private Person person;

    private PersonDTO personDTO;

    @BeforeEach
    void setUp() {
        personRepository = Mockito.mock(PersonRepository.class);
        personService = new PersonService(personRepository);

        person = new Person();
        person.setId(1L);
        person.setManager(true);

        managers = new ArrayList<>();
        managers.add(person);

        personDTO = new PersonDTO();
        personDTO.setName("Test");
    }

    //method fromDTO test - parameter is EMPLOYEE
    @Test
    void fromDTOTestEmployee() {
        personDTO.setPosition(Position.EMPLOYEE.getDescription());

        person = personService.fromDTO(personDTO);

        assertThat(person.isEmployee()).isTrue();
        assertThat(person.isManager()).isFalse();
    }

    //method fromDTO test - parameter is MANAGER
    @Test
    void fromDTOTestManager() {
        personDTO.setPosition(Position.MANAGER.getDescription());

        person = personService.fromDTO(personDTO);

        assertThat(person.isEmployee()).isFalse();
        assertThat(person.isManager()).isTrue();
    }

    //method fromDTO test - parameter is NONE
    @Test
    void fromDTOTestNone() {
        person = personService.fromDTO(personDTO);

        assertThat(person.isEmployee()).isFalse();
        assertThat(person.isManager()).isFalse();
    }

    //method getManagers test
    @Test
    void getManagersTest() {
        Mockito.when(personRepository.findByManagerTrue()).thenReturn(managers);

        List<Person> managerList = personService.getManagers();

        assertThat(managerList).isNotNull().hasSize(1);
    }

    //method savePerson test
    @Test
    void savePersonTest() {
        Mockito.when(personRepository.save(Mockito.any())).thenReturn(person);

        assertThat(personService.savePerson(person)).isNotNull().isEqualTo(person);
    }
}
