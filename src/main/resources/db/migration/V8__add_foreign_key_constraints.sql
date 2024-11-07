-- Foreign Key Constraints
ALTER TABLE Branch
    ADD CONSTRAINT FK_Branch_Address
        FOREIGN KEY (fk_Address_PK)
            REFERENCES Address (address_PK)
            ON DELETE SET NULL;

ALTER TABLE Goals
    ADD CONSTRAINT FK_Goals_Branch
        FOREIGN KEY (fk_Branch_CNPJ)
            REFERENCES Branch (CNPJ)
            ON DELETE RESTRICT;

ALTER TABLE Employees
    ADD CONSTRAINT FK_Employees_supervisor
        FOREIGN KEY (fk_supervisor_CPF)
            REFERENCES Employees (CPF)
            ON DELETE SET NULL;

ALTER TABLE Employees
    ADD CONSTRAINT FK_Employees_Branch
        FOREIGN KEY (fk_Branch_CNPJ)
            REFERENCES Branch (CNPJ)
            ON DELETE RESTRICT;

ALTER TABLE Car
    ADD CONSTRAINT FK_Car_Specification
        FOREIGN KEY (fk_Specification_specification_PK)
            REFERENCES Specification (specification_PK)
            ON DELETE SET NULL;

ALTER TABLE NewCars
    ADD CONSTRAINT FK_NewCars_Car
        FOREIGN KEY (fk_Car_chassis)
            REFERENCES Car (chassis)
            ON DELETE CASCADE;

ALTER TABLE UsedCars
    ADD CONSTRAINT FK_UsedCars_Car
        FOREIGN KEY (fk_Car_chassis)
            REFERENCES Car (chassis)
            ON DELETE CASCADE;

ALTER TABLE TradeInCredit
    ADD CONSTRAINT FK_TradeInCredit_Customer
        FOREIGN KEY (fk_Customer_CPF)
            REFERENCES Customer (CPF)
            ON DELETE CASCADE;

ALTER TABLE TradeInCredit
    ADD CONSTRAINT FK_TradeInCredit_UsedCar
        FOREIGN KEY (fk_UsedCar_chassis)
            REFERENCES UsedCars (fk_Car_chassis)
            ON DELETE CASCADE;

ALTER TABLE Sales
    ADD CONSTRAINT FK_Sales_Employees
        FOREIGN KEY (fk_Employees_CPF)
            REFERENCES Employees (CPF)
            ON DELETE CASCADE;

ALTER TABLE Sales
    ADD CONSTRAINT FK_Sales_Car
        FOREIGN KEY (fk_Car_chassis)
            REFERENCES Car (chassis)
            ON DELETE CASCADE;

ALTER TABLE Sales
    ADD CONSTRAINT FK_Sales_Customer
        FOREIGN KEY (fk_Customer_CPF)
            REFERENCES Customer (CPF)
            ON DELETE CASCADE;