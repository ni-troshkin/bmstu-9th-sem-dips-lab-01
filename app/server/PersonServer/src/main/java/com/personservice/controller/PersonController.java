package com.personservice.controller;

import com.personservice.dto.PersonRequest;
import com.personservice.dto.PersonResponse;
import com.personservice.entity.Person;
import com.personservice.mapper.PersonMapper;
import com.personservice.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@Tag(name = "PERSON")
@RequestMapping("/persons")
public class PersonController {
    /**
     * Сервис, работающий с пользователями
     */
    private final PersonService personService;

    private final PersonMapper mapper;

    public PersonController(PersonService personService, PersonMapper mapper) {
        this.personService = personService;
        this.mapper = mapper;
    }

    /**
     * Получение всех пользователей
     * @return список сущностей, описывающих пользователей
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    @Operation(summary = "Get person list")
    @GetMapping
    public ResponseEntity<ArrayList<PersonResponse>> getAllPeople() throws SQLException {
        ArrayList<Person> people = personService.getAllPeople();

        ArrayList<PersonResponse> allPeopleDTO = new ArrayList<>();
        for (Person person : people) {
            allPeopleDTO.add(mapper.toPersonResponse(person));
        }

        return ResponseEntity.status(HttpStatus.OK).body(allPeopleDTO);
    }

    /**
     * Получение информации о пользователе по его идентификатору
     * @param id идентификатор места, информацию о котором требуется получить
     * @return сущность с информацией о пользователе и статус 200 OK, если пользователь существует,
     * иначе пустое тело и статус 404 NOT FOUND
     * @throws Exception при неуспешном подключении или внутренней ошибке базы данных
     */
    @Operation(summary = "Get place by id")
    @GetMapping("/{id:\\d+}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable int id) throws Exception {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPersonResponse(person));
    }

    /**
     * Удаление пользователя по ID
     * @param id идентификатор пользователя, информацию о котором требуется удалить
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    @Operation(summary = "Delete place by id")
    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<String> deletePerson(@PathVariable int id) throws SQLException {
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Создание нового пользователя в БД
     * @param reqPerson объект с информацией, которую клиент отправил при создании пользователя
     * @return новый URI и статус 201 CREATED при успешном добавлении пользователя
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     * @throws URISyntaxException при неуспешном создании URI нового пользователя
     */
    @Operation(summary = "Create place")
    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody PersonRequest reqPerson) throws URISyntaxException, SQLException {
        int id = personService.createPerson(mapper.toPerson(reqPerson));

        URI location = new URI("/persons/" + id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(location);

        return ResponseEntity.status(HttpStatus.CREATED).headers(responseHeaders).build();
    }

    /**
     * Обновление пользователя в БД
     * @param id идентификатор пользователя, информацию о котором требуется обновить
     * @param reqPerson объект DTO с информацией, которую клиент отправил при редактировании пользователя
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    @Operation(summary = "Update place")
    @PatchMapping("/{id:\\d+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<PersonResponse> updatePlace(@PathVariable int id, @RequestBody PersonRequest reqPerson) throws SQLException {
        Person person = personService.updatePerson(id, mapper.toPerson(reqPerson));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPersonResponse(person));
    }
}
