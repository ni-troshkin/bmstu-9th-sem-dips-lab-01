package com.personservice.server.unit;

import com.personservice.entity.Person;
import com.personservice.repository.IPersonRepo;
import com.personservice.server.objectmother.PersonObjectMother;
import com.personservice.service.PersonService;
import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.personservice.server.utils.Assertions.myAssertEqual;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
    private PersonService service = null;

    private IPersonRepo rep = Mockito.mock(IPersonRepo.class);

    @Before
    public void setUp() {
//        Person person = PersonObjectMother.getPerson();
        service = new PersonService(rep);
    }

    @Test
    @Description("Тестирование получения пользователя по ID")
    public void checkGetPersonById() throws SQLException {
        // arrange
        Person person = PersonObjectMother.getPerson();
        Mockito.when(service.getPersonById(2)).thenReturn(person);

        // act
        Person resultPerson = service.getPersonById(2);

        // assert
        Mockito.verify(rep).getPersonById(2);
        myAssertEqual(person, resultPerson);
    }

    @Test
    @Description("Тестирование получения всех пользователей")
    public void checkGetAllPeople() throws SQLException {
        // arrange
        ArrayList<Person> lst = PersonObjectMother.getPersonList();
        Mockito.when(service.getAllPeople()).thenReturn(lst);

        // act
        ArrayList<Person> resultLst = service.getAllPeople();

        // assert
        Mockito.verify(rep).getAll();
        assertEquals(lst.size(), resultLst.size());
        for (int i = 0; i < resultLst.size(); i++) {
            myAssertEqual(lst.get(i), resultLst.get(i));
        }
    }

    @Test
    @Description("Тестирование добавления пользователя")
    public void checkCreatePerson() throws SQLException {
        // arrange
        Person person = PersonObjectMother.getAnotherPerson();

        // act
        service.createPerson(person);

        // assert
        Mockito.verify(rep).createPerson(person);
    }

    @Test
    @Description("Тестирование удаления пользователя")
    public void checkDeletePerson() throws SQLException {
        // act
        service.deletePerson(2);

        // assert
        Mockito.verify(rep).deletePerson(2);
    }

    @Test
    @Description("Тестирование обновления пользователя")
    public void checkUpdatePerson() throws SQLException {
        // arrange
        Person person = PersonObjectMother.getPerson();
        Mockito.when(service.updatePerson(2, person)).thenReturn(person);

        // act
        Person result = service.updatePerson(2, person);

        // assert
        Mockito.verify(rep).updatePerson(2, person);
        myAssertEqual(person, result);
    }
}
