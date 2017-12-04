package com.pinky83.photo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photos")
public class Photo extends BaseEntity {

    @NotNull @Max(100)
    private String name;

    @NotNull @Max(100)
    private String file;

    @DefaultValue("false")
    @Column(name = "is_human")
    private Boolean isHuman;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_counter")
    @SequenceGenerator(name = "photo_counter", sequenceName = "photo_counter", allocationSize = 1)
    private Integer counter;
}
