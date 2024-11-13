package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Sales;
import com.cesarschool.autoline_honda.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public Sales createSale(Sales sale) {
        salesRepository.saveSale(sale);
        return sale;
    }

    public Sales updateSale(Sales sale) {
        salesRepository.updateSale(sale);
        return sale;
    }

    public void deleteSaleBySaleId(int saleId) {
        salesRepository.deleteSaleBySaleId(saleId);
    }

    public Optional<Sales> findSaleBySaleId(int saleId) {
        return salesRepository.findSaleBySaleId(saleId);
    }

    public List<Sales> findAllSales() {
        return salesRepository.findAllSales();
    }
}
