-- Employees Table: Stores employee details, with optional self-referencing supervisor structure
CREATE TABLE Employees (
    name VARCHAR(255),
    CPF VARCHAR(11) PRIMARY KEY,
    salary DECIMAL(10, 2),
    position VARCHAR(255),
    fk_supervisor_CPF VARCHAR(11),
    fk_Branch_CNPJ VARCHAR(14)
);