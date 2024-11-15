CREATE TABLE Customer
(
    cpf            VARCHAR(11) PRIMARY KEY,
    name           VARCHAR(255) NOT NULL,
    driver_license VARCHAR(12)  NOT NULL UNIQUE,
    birth_date     DATE,
    neighborhood   VARCHAR(100),
    address_number INT,
    state          VARCHAR(2),
    zip_code       VARCHAR(8),
    street         VARCHAR(255),
    city           VARCHAR(100)
);