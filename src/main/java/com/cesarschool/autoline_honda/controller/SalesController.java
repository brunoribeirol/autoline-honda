package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Sales;
import com.cesarschool.autoline_honda.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public ResponseEntity<Sales> createSale(@RequestBody Sales sale) {
        try {
            Sales createdSale = salesService.createSale(sale);
            return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{saleId}")
    public ResponseEntity<Sales> getSaleBySaleId(@PathVariable int saleId) {
        Optional<Sales> saleOptional = salesService.findSaleBySaleId(saleId);
        return saleOptional.map(sale -> new ResponseEntity<>(sale, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> sales = salesService.findAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }


    @PutMapping("/{saleId}")
    public ResponseEntity<Sales> updateSale(@PathVariable int saleId, @RequestBody Sales sale) {
        sale.setSaleId(saleId);
        try {
            Sales updatedSale = salesService.updateSale(sale);
            return new ResponseEntity<>(updatedSale, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable int saleId) {
        try {
            salesService.deleteSaleBySaleId(saleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
