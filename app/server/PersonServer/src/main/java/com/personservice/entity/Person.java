package com.personservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Person {
    int id;
    String name;
    int age;
    String address;
    String work;
}
