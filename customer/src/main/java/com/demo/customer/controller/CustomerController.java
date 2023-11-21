package com.demo.customer.controller;

import com.demo.customer.entity.Customer;
import com.demo.customer.repository.CustomerProductRepository;
import com.demo.customer.repository.CustomerRepository;
import com.demo.customer.service.CustomerService;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private WebClient.Builder webclientBuilder;
    HttpClient client = HttpClient.create();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerProductRepository customerProductRepository;
/*
    @Autowired
    private CustomerService customerRepositoryService;*/

    @GetMapping
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Customer> findById(@PathVariable Long id) {
        return customerRepository.findById(id);
    }

    @PostMapping
    public void post(@RequestBody Customer customer) {
        customer.getProducts().forEach(customerProduct -> {
            customerProduct.setCustomer(customer);
        });
        customerRepository.save(customer);

        customer.getProducts().forEach(customerProduct -> {
            String productName = getProductNameById(customerProduct.getIdProducto());
            customerProduct.setCustomer(customer);
            customerProductRepository.save(customerProduct);
        });
    }


    private String getProductNameById(Long id) {
        WebClient webClient = webclientBuilder.clientConnector(new ReactorClientHttpConnector(client))
                .baseUrl("http://localhost:8082/product")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8082/product"))
                .build();

        JsonNode node = webClient.method(HttpMethod.GET).uri("/" + id)
                .retrieve().bodyToMono(JsonNode.class).block();

        //ProductDTO cliente = new Gson().fromJson(node.toString(), ProductDTO.class);

        return node.get("name").asText();
    }

    @GetMapping("/nombre")
    public Customer findByName(@RequestPart String name) {
        return customerRepository.findByName(name);
    }
}
