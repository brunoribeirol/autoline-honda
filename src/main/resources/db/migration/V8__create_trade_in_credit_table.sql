CREATE TABLE TradeInCredit
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    customer_cpf     VARCHAR(11),
    used_car_chassis VARCHAR(17),
    value            DECIMAL(10, 2) CHECK (value > 0),

    CONSTRAINT fk_TradeInCredit_Customer FOREIGN KEY (customer_cpf)
        REFERENCES Customer (cpf),
    CONSTRAINT fk_TradeInCredit_UsedCar FOREIGN KEY (used_car_chassis)
        REFERENCES Car (chassis)
);