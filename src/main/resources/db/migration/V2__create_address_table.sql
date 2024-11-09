CREATE TABLE Address
(
    branch_cnpj    VARCHAR(14) PRIMARY KEY,
    zip_code       VARCHAR(8)   NOT NULL, -- int(8) int WITH width deprecated -> the right is just int
    street         VARCHAR(255) NOT NULL,
    address_number INT          NOT NULL,
    neighborhood   VARCHAR(100) NOT NULL,
    city           VARCHAR(100) NOT NULL,
    state          VARCHAR(2) DEFAULT 'PE',

    CONSTRAINT fk_Address_Branch FOREIGN KEY (branch_cnpj)
        REFERENCES Branch (cnpj) ON DELETE CASCADE
);