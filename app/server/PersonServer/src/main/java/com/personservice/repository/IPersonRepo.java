package com.personservice.repository;

import com.personservice.entity.Person;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Интерфейс репозитория используется для работы с таблицей, отвечающей за места в базе данных
 */
public interface IPersonRepo {
    /**
     * Получение информации всех пользователях
     * @return список объектов Person, содержащий информацию о всех пользователях
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    ArrayList<Person> getAll() throws SQLException;

    /**
     * Получение информации о месте по ее идентификатору
     * @param id идентификатор места, информацию о котором требуется получить
     * @return объект Person с информацией из БД о заданном месте
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    Person getPersonById(int id) throws SQLException;

    /**
     * Удаление места по ID
     * @param id идентификатор места, информацию о котором требуется удалить
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    void deletePerson(int id) throws SQLException;

    /**
     * Создание нового места в БД
     * @param place объект с информацией, которую пользователь ввел при создании карточки места
     * @return идентификатор, назначенный в БД новому месту
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    int createPerson(Person place) throws SQLException;

    /**
     * Обновление нового места в БД
     * @param id идентификатор места, информацию о котором требуется обновить
     * @param person объект с информацией, которую пользователь ввел при редактировании карточки места
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    Person updatePerson(int id, Person person) throws SQLException;
}
