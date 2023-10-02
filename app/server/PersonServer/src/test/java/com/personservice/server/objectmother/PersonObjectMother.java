package com.personservice.server.objectmother;

import com.personservice.dto.PersonRequest;
import com.personservice.entity.Person;
import com.personservice.mapper.PersonMapper;

import java.util.ArrayList;
import java.util.List;

public class PersonObjectMother {
    public static Person getPerson() {
        return new Person(2, "Имя", 24, "Тверь", "Газпром");
    }

    public static Person getAnotherPerson() {
        return new Person(1, "Имя 1", 32, "Москва", "Билайн");
    }

    public static ArrayList<Person> getPersonList() {
        ArrayList<Person> lst = new ArrayList<>();
        lst.add(getPerson());
        lst.add(getAnotherPerson());
        return lst;
    }
}
