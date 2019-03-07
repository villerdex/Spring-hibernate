package com.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
 public class Person {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    @MapsId
    private Address address;

    @OneToMany(
           mappedBy = "person",
           cascade = CascadeType.ALL,
           orphanRemoval = true
   )

   private List<Orders> orderList = new ArrayList<Orders>();

   public void addOrder(Orders order) {
      order.setPerson(this);
      orderList.add(order);
   }

   public void removeOrder(Orders order) {
      order.setPerson(null);
      orderList.remove(order);
   }

}
