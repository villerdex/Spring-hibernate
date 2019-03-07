package com.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
 public class Item {

    @Id
    @GeneratedValue
    private int Id;

    private String name;

    private int price;

    @ManyToMany(mappedBy = "items")
    private Set<Orders> orders = new HashSet<Orders>();
}
