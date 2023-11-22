package com.demo.customer.service;
import com.demo.customer.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;


@Service
public class CustomerService {

    @PersistenceContext
    EntityManager em;

    public Customer findByName(String name) {
        String consulta = "SELECT c FROM Customer c WHERE c.name = :name";
        TypedQuery<Customer> query = em.createQuery(consulta, Customer.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult();
        } catch (NoResultException ex) {
            return  null;
        }
    }

}