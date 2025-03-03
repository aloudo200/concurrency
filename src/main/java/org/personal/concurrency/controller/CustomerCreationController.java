package org.personal.concurrency.controller;

import org.personal.concurrency.exception.CustomerNotFoundException;
import org.personal.concurrency.model.CustomersEntity;
import org.personal.concurrency.service.CustomerCreationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/rest/customer")
public class CustomerCreationController {

    private final CustomerCreationServiceImpl customerCreationService;

    public CustomerCreationController(CustomerCreationServiceImpl customerCreationService) {
        this.customerCreationService = customerCreationService;
    }

    @PostMapping("/createNew")
    public ResponseEntity<String> createCustomer(String firstName, String lastName, String email) {
        try {
            customerCreationService.createCustomer(firstName, lastName, email);
        } catch (Exception e) {
            return new ResponseEntity<>(String.format("Error creating customer: %s", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Customer created successfully and persisted to database", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getCustomerById(@PathVariable long id) {
        try {
            CustomersEntity customer = customerCreationService.getCustomerById(id);
            return new ResponseEntity<>(customer.toString(), HttpStatus.OK);
        } catch (CustomerNotFoundException cnf) {
            return new ResponseEntity<>(cnf.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(String.format("Error fetching customer: %s", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
