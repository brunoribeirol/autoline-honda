package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Sales;

import java.util.List;
import java.util.Optional;

public interface SalesRepository {
    int saveSale(Sales sale);
    int updateSale(Sales sale);
    int deleteSaleBySaleId(int saleId);
    Optional<Sales> findSaleBySaleId(int saleId);
    List<Sales> findAllSales();
}
