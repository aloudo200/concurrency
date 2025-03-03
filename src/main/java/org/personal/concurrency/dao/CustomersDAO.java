package org.personal.concurrency.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.personal.concurrency.model.CustomersEntity;
import org.springframework.stereotype.Repository;

@Repository
public class CustomersDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void persistCustomerRecord(CustomersEntity customersEntity) {
        entityManager.persist(customersEntity);
    }

    public CustomersEntity getCustomerById(long id) {
        return entityManager.find(CustomersEntity.class, id);
    }
}
