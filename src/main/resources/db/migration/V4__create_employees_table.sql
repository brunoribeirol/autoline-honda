CREATE TABLE Employees
(
    name           VARCHAR(255)   NOT NULL,
    cpf            VARCHAR(11) PRIMARY KEY,
    salary         DECIMAL(10, 2) NOT NULL CHECK (salary > 0),
    position       VARCHAR(255)   NOT NULL,
    supervisor_cpf VARCHAR(11),
    branch_cnpj    VARCHAR(14),

    CONSTRAINT fk_Employees_Supervisor FOREIGN KEY (supervisor_cpf)
        REFERENCES Employees (cpf) ON DELETE SET NULL,
    CONSTRAINT fk_Employees_Branch FOREIGN KEY (branch_cnpj)
        REFERENCES Branch (cnpj) ON DELETE CASCADE
);