package com.personservice.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private int id;
    private String name;
    private int age;
    private String address;
    private String work;
}
