CREATE TABLE CustomerPhone
(
    customer_cpf VARCHAR(11),
    phone_number VARCHAR(15) UNIQUE,

    CONSTRAINT fk_CustomerPhone_Customer FOREIGN KEY (customer_cpf)
        REFERENCES Customer (cpf) ON DELETE CASCADE
);