package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Sales;

import java.util.List;
import java.util.Optional;

public interface SalesRepository {
    // Save a new sale
    int saveSale(Sales sale);

    // Update an existing sale
    int updateSale(Sales sale);

    // Delete a sale by its saleId
    int deleteSaleBySaleId(int saleId);

    // Find a sale by its saleId
    Optional<Sales> findSaleBySaleId(int saleId);

    // Find all sales
    List<Sales> findAllSales();
}
