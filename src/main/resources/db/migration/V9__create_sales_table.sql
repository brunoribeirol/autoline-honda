CREATE TABLE Sales
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    employee_cpf VARCHAR(11),
    car_chassis  VARCHAR(17),
    customer_cpf VARCHAR(11),
    final_price  DECIMAL(10, 2) CHECK (final_price > 0),
    sale_status  VARCHAR(50),
    sale_date    DATE,
    discount     DECIMAL(10, 2) CHECK (discount >= 0),

    CONSTRAINT fk_Sales_Employee FOREIGN KEY (employee_cpf)
        REFERENCES Employees (CPF) ON DELETE CASCADE,
    CONSTRAINT fk_Sales_Car FOREIGN KEY (car_chassis)
        REFERENCES Car (chassis) ON DELETE CASCADE,
    CONSTRAINT fk_Sales_Customer FOREIGN KEY (customer_cpf)
        REFERENCES Customer (CPF) ON DELETE CASCADE
);