DELIMITER $$

CREATE FUNCTION calculate_final_price(customerCpf VARCHAR(11), carPrice DECIMAL(10, 2))
    RETURNS DECIMAL(10, 2)
    DETERMINISTIC
BEGIN
    DECLARE tradeInValue DECIMAL(10, 2);

    SELECT value
    INTO tradeInValue
    FROM TradeInCredit
    WHERE customer_cpf = customerCpf;

    IF tradeInValue IS NOT NULL THEN
        RETURN carPrice - tradeInValue;
    ELSE
        RETURN carPrice;
    END IF;
END $$

DELIMITER ;