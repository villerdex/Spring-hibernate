package com.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
 public class Address {

    @Id
    @GeneratedValue
    private int id;

    private String address;

}
