CREATE TABLE Car
(
    chassis       VARCHAR(17) PRIMARY KEY,
    price         DECIMAL(10, 2) NOT NULL CHECK (price > 0),
    color         VARCHAR(50)    NOT NULL,
    wheel_size    INT            NOT NULL CHECK (wheel_size > 0),
    fuel_type     VARCHAR(50)    NOT NULL CHECK (fuel_type IN ('Gasoline', 'Diesel', 'Electric', 'Hybrid')),
    year          INT            NOT NULL CHECK (year >= 1886),
    engine        VARCHAR(50)    NOT NULL,
    transmission  VARCHAR(50)    NOT NULL CHECK (transmission IN ('Manual', 'Automatic', 'CVT')),
    mileage       INT            NOT NULL CHECK (mileage >= 0),
    car_condition VARCHAR(10) AS (CASE WHEN mileage = 0 THEN 'New' ELSE 'Used' END) STORED
);