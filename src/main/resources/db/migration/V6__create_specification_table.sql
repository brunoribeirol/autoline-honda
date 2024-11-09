CREATE TABLE Specification
(
    category    VARCHAR(100) NOT NULL,
    model       VARCHAR(100) NOT NULL,
    version     VARCHAR(100) NOT NULL,
    car_chassis VARCHAR(17),

    CONSTRAINT fk_Specification_Car FOREIGN KEY (car_chassis)
        REFERENCES Car (chassis) ON DELETE CASCADE
);