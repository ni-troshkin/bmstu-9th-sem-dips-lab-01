package com.personservice.mapper;

import com.personservice.dto.PersonRequest;
import com.personservice.dto.PersonResponse;
import com.personservice.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    /**
     * Конвертация из сущности БД в сущность DTO Response
     * @param person сущность БД, описывающая пользователя
     * @return DTO с информацией о пользователе
     */
    public PersonResponse toPersonResponse(Person person) {
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .age(person.getAge())
                .address(person.getAddress())
                .work(person.getWork())
                .build();
    }

    /**
     * Конвертация из сущности DTO Request в сущность БД
     * @param req структура, приходящая от клиента
     * @return Сущность с информацией о пользователе
     */
    public Person toPerson(PersonRequest req) {
        return Person.builder()
                .id(0)
                .name(req.getName())
                .age(req.getAge())
                .address(req.getAddress())
                .work(req.getWork())
                .build();
    }
}
