CREATE TABLE Goals
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    goal_date    DATE NOT NULL,
    car_quantity INT  NOT NULL CHECK (car_quantity > 0),
    branch_cnpj  VARCHAR(14),

    CONSTRAINT fk_Goals_Branch FOREIGN KEY (branch_cnpj)
        REFERENCES Branch (cnpj) ON DELETE CASCADE
);