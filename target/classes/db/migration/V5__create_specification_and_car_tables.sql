-- Specification Table: Stores car specifications
CREATE TABLE Specification (
    specification_PK INT PRIMARY KEY AUTO_INCREMENT,
    category VARCHAR(100),
    model VARCHAR(100),
    version VARCHAR(100)
    );

-- Car Table: Stores car details, references a specification table
CREATE TABLE Car (
    chassis VARCHAR(17) PRIMARY KEY,
    price DECIMAL(10, 2),
    color VARCHAR(50),
    wheels INT,
    fuel_type VARCHAR(50),
    fk_Specification_specification_PK INT,
    year INT,
    engine VARCHAR(50),
    transmission VARCHAR(50)
);

-- NewCars Table: For new cars
CREATE TABLE NewCars (
    fk_Car_chassis VARCHAR(17) PRIMARY KEY
);

-- UsedCars Table: For used cars
CREATE TABLE UsedCars (
    mileage INT,
    fk_Car_chassis VARCHAR(17) PRIMARY KEY
);
