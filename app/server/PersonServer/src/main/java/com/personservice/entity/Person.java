package com.personservice.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Person {
    int id;
    String name;
    int age;
    String address;
    String work;
}
