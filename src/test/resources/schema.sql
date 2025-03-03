-- src/test/resources/schema.sql

CREATE TABLE JAVA_CONCURRENCY.customers (
                                            customer_id NUMBER,
                                            first_name VARCHAR2(50) NOT NULL,
                                            last_name VARCHAR2(50) NOT NULL,
                                            email VARCHAR2(50) NOT NULL,
                                            total_purchases NUMBER(10,2) DEFAULT 0,
                                            PRIMARY KEY (customer_id),
                                            UNIQUE (email)
);

CREATE SEQUENCE JAVA_CONCURRENCY.CUSTOMER_ID_SEQ START WITH 100 INCREMENT BY 1;

CREATE OR REPLACE TRIGGER JAVA_CONCURRENCY.CUSTOMER_ID_TRIGGER
    BEFORE INSERT ON JAVA_CONCURRENCY.customers
    FOR EACH ROW
BEGIN
    :NEW.customer_id := JAVA_CONCURRENCY.CUSTOMER_ID_SEQ.NEXTVAL;
END;