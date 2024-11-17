DELIMITER !!

CREATE TRIGGER set_discount_before_insert
    BEFORE INSERT
    ON Sales
    FOR EACH ROW
BEGIN
    DECLARE trade_value DECIMAL(10, 2);

    SELECT value
    INTO trade_value
    FROM TradeInCredit
    WHERE customer_cpf = NEW.customer_cpf;

    IF trade_value IS NOT NULL THEN
        SET NEW.discount = trade_value;
    ELSE
        SET NEW.discount = 0;
    END IF;

    SET NEW.final_price = NEW.final_price - NEW.discount;
END !!

DELIMITER ;