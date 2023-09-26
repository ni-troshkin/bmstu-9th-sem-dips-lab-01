package com.personservice.service;

import com.personservice.entity.Person;
import com.personservice.repository.IPersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Класс в слое сервисов, обращающийся к репозиторию пользователей
 */
@Service
public class PersonService {
    /**
     * Репозиторий, работающий с местами в базе данных
     */
    @Autowired
    private final IPersonRepo repo;

    public PersonService(IPersonRepo repo) {
        this.repo = repo;
    }

    /**
     * Получение всех пользователей из БД
     * @return список сущностей, описывающих пользователей
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    public ArrayList<Person> getAllPeople() throws SQLException {
        return repo.getAll();
    }

    /**
     * Получение информации о пользователе по его идентификатору
     * @param id идентификатор пользователя, информацию о котором требуется получить
     * @return объект Person с информацией из БД о заданном пользователе
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    public Person getPersonById(int id) throws SQLException {
        return repo.getPersonById(id);
    }

    /**
     * Удаление места по ID
     * @param id идентификатор пользователя, информацию о котором требуется удалить
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    public void deletePerson(int id) throws SQLException {
        repo.deletePerson(id);
    }

    /**
     * Создание нового пользователя в БД
     * @param person объект с информацией, которую клиент ввел при создании пользователя
     * @return идентификатор, назначенный в БД новому пользователю
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    public int createPerson(Person person) throws SQLException {
        return repo.createPerson(person);
    }

    /**
     * Обновление пользователя в БД
     * @param id идентификатор пользователя, информацию о котором требуется обновить
     * @param person объект с информацией, которую клиент ввел при редактировании пользователя
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    public Person updatePerson(int id, Person person) throws SQLException {
        return repo.updatePerson(id, person);
    }
}
