package com.example.customer.persistence.entity;

import javax.persistence.*;
import java.util.function.Function;

@Entity
@Table(name = "tb_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    private String name;

    @Column(name = "document", nullable = false)
    private String document;



    public <R> R map(Function<Customer, R> func){
        return func.apply(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
