package com.personservice.repository;

import com.personservice.entity.Person;
import com.personservice.utils.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

/**
 * Репозиторий используется для работы с таблицей places базы данных PostgreSQL
 * Для подключения используется драйвер JDBC
 */
@Repository
public class PgPersonRepo implements IPersonRepo {
    /**
     * Объект подключения к БД
     */
    private final Connection conn = ConnectionManager.open();

    /**
     * Получение информации всех пользователях
     * @return список объектов Person, содержащий информацию о всех пользователях
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    @Override
    public ArrayList<Person> getAll() throws SQLException {
        ArrayList<Person> people = new ArrayList<>();

        String getPeople = "SELECT id_person, name, age, address, work " +
                "FROM public.person";

        PreparedStatement userPlaceQuery = conn.prepareStatement(getPeople);
        ResultSet rs = userPlaceQuery.executeQuery();

        while (rs.next()) {
            people.add(Person.builder()
                    .id(rs.getInt("id_person"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .address(rs.getString("address"))
                    .work(rs.getString("work"))
                    .build());
        }

        return people;
    }

    /**
     * Получение информации о пользователе по его идентификатору
     * @param id идентификатор пользователя, информацию о котором требуется получить
     * @return объект Person с информацией из БД о заданном пользователе
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    @Override
    public Person getPersonById(int id) throws SQLException {
        Person person = null;

        String getPerson = "SELECT id_person, name, age, address, work " +
                "FROM public.person WHERE id_person = ?";

        PreparedStatement personQuery = conn.prepareStatement(getPerson);
        personQuery.setInt(1, id);
        ResultSet rs = personQuery.executeQuery();

        while (rs.next()) {
            person = Person.builder()
                    .id(rs.getInt("id_person"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .address(rs.getString("address"))
                    .work(rs.getString("work"))
                    .build();
        }

        return person;
    }

    /**
     * Удаление места по ID
     * @param id идентификатор пользователя, информацию о котором требуется удалить
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    @Override
    public void deletePerson(int id) throws SQLException {
        String deletePerson = "DELETE FROM public.person WHERE id_person = ?";

        PreparedStatement personDeletion = conn.prepareStatement(deletePerson);
        personDeletion.setInt(1, id);
        personDeletion.executeUpdate();
    }

    /**
     * Создание нового места в БД
     * @param person объект с информацией, которую пользователь ввел при создании своей записи
     * @return идентификатор, назначенный в БД новому месту
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    @Override
    public int createPerson(Person person) throws SQLException {
        int id;
        try {
            String personAdd = "INSERT INTO public.person " +
                                "(name, age, address, work) " +
                                "VALUES (?, ?, ?, ?)";

            PreparedStatement personInsertion = conn.prepareStatement(personAdd);
            personInsertion.setString(1, person.getName());
            personInsertion.setInt(2, person.getAge());
            personInsertion.setString(3, person.getAddress());
            personInsertion.setString(4, person.getWork());
            personInsertion.executeUpdate();

            String checkId = "SELECT id_person FROM public.person " +
                                "WHERE name = ? AND age = ?";
            PreparedStatement check = conn.prepareStatement(checkId);
            check.setString(1, person.getName());
            check.setInt(2, person.getAge());

            ResultSet rs = check.executeQuery();
            rs.next();
            id = rs.getInt("id_person");
        } catch (SQLException e) {
            e.printStackTrace();

            throw new SQLException("Не удалось добавить человека " +
                        "в базу данных");
        }

        return id;
    }

    /**
     * Обновление нового места в БД
     * @param id идентификатор человека, информацию о котором требуется обновить
     * @param person объект с информацией, которую пользователь ввел при редактировании своих данных
     * @throws SQLException при неуспешном подключении или внутренней ошибке базы данных
     */
    @Override
    public Person updatePerson(int id, Person person) throws SQLException {
        String updPerson = "UPDATE public.person " +
                "SET (name, age, address, work) = (?, ?, ?, ?) " +
                "WHERE id_person = ?";

        PreparedStatement personUpdate = conn.prepareStatement(updPerson);
        personUpdate.setString(1, person.getName());
        personUpdate.setInt(2, person.getAge());
        personUpdate.setString(3, person.getAddress());
        personUpdate.setString(4, person.getWork());
        personUpdate.setInt(5, id);
        personUpdate.executeUpdate();

        return getPersonById(id);
    }
}
