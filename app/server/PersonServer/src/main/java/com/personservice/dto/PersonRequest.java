package com.personservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private String name;
    private int age;
    private String address;
    private String work;
}
