DELIMITER $$

CREATE TRIGGER check_final_price_discount_before_insert
    BEFORE INSERT
    ON Sales
    FOR EACH ROW
BEGIN
    IF NEW.final_price <= NEW.discount THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Final price must be greater than discount.';
    END IF;
END $$

CREATE TRIGGER check_final_price_discount_before_update
    BEFORE UPDATE
    ON Sales
    FOR EACH ROW
BEGIN
    IF NEW.final_price <= NEW.discount THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Final price must be greater than discount.';
    END IF;
END $$

DELIMITER ;