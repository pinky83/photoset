package com.pinky83.photo.dao;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="global_seq")
    @SequenceGenerator(name="global_seq", sequenceName="global_seq", allocationSize=1)
    private int id;

}
