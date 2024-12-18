package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Sales;
import com.cesarschool.autoline_honda.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesService(SalesRepository salesRepository, JdbcTemplate jdbcTemplate) {
        this.salesRepository = salesRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Sales createSale(Sales sale) {
        float calculatedPrice = calculateFinalPrice(sale.getCustomerCpf(), sale.getCarChassis());
        float discount = calculateDiscount(sale.getCustomerCpf());

        sale.setFinalPrice(calculatedPrice);
        sale.setDiscount(discount);

        salesRepository.saveSale(sale);
        return sale;
    }

    public Sales updateSale(Sales sale) {
        Optional<Sales> existingSaleOpt = salesRepository.findSaleBySaleId(sale.getSaleId());

        if (existingSaleOpt.isEmpty()) {
            throw new RuntimeException("Sale not found");
        }

        Sales existingSale = existingSaleOpt.get();

        if (sale.getCarChassis() != null) {
            existingSale.setCarChassis(sale.getCarChassis());
        }
        if (sale.getSaleStatus() != null) {
            existingSale.setSaleStatus(sale.getSaleStatus());
        }

        float calculatedPrice = calculateFinalPrice(existingSale.getCustomerCpf(), existingSale.getCarChassis());
        float discount = calculateDiscount(existingSale.getCustomerCpf());

        existingSale.setFinalPrice(calculatedPrice);
        existingSale.setDiscount(discount);

        salesRepository.updateSale(existingSale);
        return existingSale;
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

    private float calculateFinalPrice(String customerCpf, String carChassis) {
        String carPriceQuery = "SELECT price FROM Car WHERE chassis = ?";
        Float carPrice = jdbcTemplate.queryForObject(carPriceQuery, Float.class, carChassis);

        if (carPrice == null) {
            throw new RuntimeException("Car not found");
        }

        float discount = calculateDiscount(customerCpf);
        return carPrice - discount;
    }

    private float calculateDiscount(String customerCpf) {
        String creditQuery = "SELECT value FROM TradeInCredit WHERE customer_cpf = ?";
        Float tradeInValue = jdbcTemplate.queryForObject(creditQuery, Float.class, customerCpf);
        return tradeInValue != null ? tradeInValue : 0.0f;
    }
}
