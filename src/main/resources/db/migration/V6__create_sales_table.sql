-- Sales Table: Tracks sales
CREATE TABLE Sales
(
    fk_Employees_CPF VARCHAR(11),
    fk_Car_chassis   VARCHAR(17),
    fk_Customer_CPF  VARCHAR(11),
    id               INT PRIMARY KEY AUTO_INCREMENT,
    final_price      DECIMAL(10, 2),
    sale_status      VARCHAR(50),
    date             DATE,
    discount         DECIMAL(10, 2)
);