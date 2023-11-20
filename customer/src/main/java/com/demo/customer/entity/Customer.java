package com.demo.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Customer implements Serializable {

    private static final long serialVersionUID = 8807952775925691451L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String phone;

    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CustomerProduct> customerProducts;

}
