package org.personal.concurrency.service;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.personal.concurrency.dao.CustomersDAO;
import org.personal.concurrency.exception.CustomerNotFoundException;
import org.personal.concurrency.model.CustomersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerCreationServiceImpl {

    private final CustomersDAO customersDAO;


    @Autowired
    public CustomerCreationServiceImpl(CustomersDAO customersDAO) {
        this.customersDAO = customersDAO;
    }

    @Transactional
    public void createCustomer(String firstName, String lastName, String email) {
        CustomersEntity customersEntity = new CustomersEntity(firstName, lastName, email);
        customersDAO.persistCustomerRecord(customersEntity);
    }

    public CustomersEntity getCustomerById(long id) {
        Optional<CustomersEntity> foundCustomer = Optional.ofNullable(customersDAO.getCustomerById(id));
        if(foundCustomer.isPresent()) {
            return foundCustomer.get();
        } else {
            throw new CustomerNotFoundException(String.format("Customer with id '%s' does not exist", id));
        }
    }
}
