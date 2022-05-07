package ru.itmo.kotiki.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatDto {
    private int id;
    private String name;
    private Date dateOfBirth;
    private String breed;
    private String color;
    private int ownerId;
}

