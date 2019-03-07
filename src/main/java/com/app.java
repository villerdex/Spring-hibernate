package com;

import com.models.Address;
import com.models.Item;
import com.models.Orders;
import com.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class app {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(app.class, args);

        sessionFactory = setUp();
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // one to one unidirectional persist
        Address address = new Address();
        address.setAddress("new address");

        Person person = new Person();
        person.setName("Orville");
        person.setAddress(address);

        session.save(person);
        session.getTransaction().commit();

        // one to many bidirectional persist
        session.getTransaction().begin();

        Address address2 = new Address();
        address2.setAddress("new Address 2");

        Person person2 = new Person();
        person2.setName("Person 2");
        person2.setAddress(address2);

        Orders order =  new Orders();
        Orders order2 =  new Orders();

        person2.addOrder(order);
        person2.addOrder(order2);

        session.save(person2);
        session.getTransaction().commit();

        // many to many persist bidirectional
        session.getTransaction().begin();

        Orders order3 =  new Orders();
        Item item = new Item();
        item.setName("item1");
        item.setPrice(2);
        
        Item item2 = new Item();
        item2.setName("item2");
        item2.setPrice(2);


        order3.addItem(item);
        order3.addItem(item2);

        Orders order4 =  new Orders();
        Item item3 = new Item();
        item3.setName("item3");
        item3.setPrice(3);

        order4.addItem(item3);

        session.save(order3);
        session.save(order4);

        order4.removeItem(item3);
        session.getTransaction().commit();
        session.close();


    }
    private static SessionFactory setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            return sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            e.printStackTrace();
        }
        return null;
    }
}
