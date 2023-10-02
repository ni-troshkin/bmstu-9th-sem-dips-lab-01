package com.personservice.server.utils;

import com.personservice.entity.Person;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Assertions {
    public static void myAssertEqual(Person p1, Person p2) {
        assertEquals(p1.getId(), p2.getId());
        assertEquals(p1.getName(), p2.getName());
        assertEquals(p1.getAge(), p2.getAge());
        assertEquals(p1.getAddress(), p2.getAddress());
        assertEquals(p1.getWork(), p2.getWork());
    }
}
