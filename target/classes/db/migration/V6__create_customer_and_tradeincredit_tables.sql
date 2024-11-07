-- Customer Table: Stores client details with address fields
CREATE TABLE Customer (
    CPF VARCHAR(11) PRIMARY KEY,
    name VARCHAR(255),
    driver_license VARCHAR(12),
    phone VARCHAR(15),
    birth_date DATE,
    neighborhood VARCHAR(100),
    number INT,
    state VARCHAR(2),
    zip_code VARCHAR(8),
    street VARCHAR(255),
    city VARCHAR(100)
);

-- TradeInCredit Table: Tracks credit given for used cars in trade-ins
CREATE TABLE TradeInCredit (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fk_Customer_CPF VARCHAR(11),
    fk_UsedCar_chassis VARCHAR(17),
    value DECIMAL(10, 2)
);