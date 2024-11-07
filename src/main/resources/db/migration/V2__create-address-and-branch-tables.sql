-- Address Table: Stores addresses for branches
CREATE TABLE Address (
     address_PK VARCHAR(36) PRIMARY KEY,
     zip_code VARCHAR(8),
     street VARCHAR(255),
     number INT,
     neighborhood VARCHAR(100),
     city VARCHAR(100),
     state VARCHAR(2)
);

-- Branch Table: Stores information about branches
CREATE TABLE Branch (
    CNPJ VARCHAR(14) PRIMARY KEY,
    name VARCHAR(255),
    fk_Address_PK VARCHAR(36)
);
