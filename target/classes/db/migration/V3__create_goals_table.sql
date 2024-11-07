-- Goals Table: Tracks goals/targets for each branch
CREATE TABLE Goals (
   ID INT PRIMARY KEY AUTO_INCREMENT,
   date DATE,
   car_quantity INT,
   fk_Branch_CNPJ VARCHAR(14)
);