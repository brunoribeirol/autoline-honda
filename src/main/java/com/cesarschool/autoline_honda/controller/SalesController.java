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
@RequestMapping("/sales")  // Define the base URL for all sales-related endpoints
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    // Endpoint to create a new sale
    @PostMapping
    public ResponseEntity<Sales> createSale(@RequestBody Sales sale) {
        try {
            Sales createdSale = salesService.createSale(sale);
            return new ResponseEntity<>(createdSale, HttpStatus.CREATED);  // Return status 201
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to get a sale by saleId
    @GetMapping("/{saleId}")
    public ResponseEntity<Sales> getSaleBySaleId(@PathVariable int saleId) {
        Optional<Sales> saleOptional = salesService.findSaleBySaleId(saleId);
        return saleOptional.map(sale -> new ResponseEntity<>(sale, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));  // Return 404 if not found
    }

    // Endpoint to get all sales
    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        List<Sales> sales = salesService.findAllSales();
        return new ResponseEntity<>(sales, HttpStatus.OK);  // Return status 200
    }

    // Endpoint to update a sale
    @PutMapping("/{saleId}")
    public ResponseEntity<Sales> updateSale(@PathVariable int saleId, @RequestBody Sales sale) {
        sale.setSaleId(saleId);  // Ensure the saleId is set in the sale object
        try {
            Sales updatedSale = salesService.updateSale(sale);
            return new ResponseEntity<>(updatedSale, HttpStatus.OK);  // Return status 200
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);  // If something goes wrong
        }
    }

    // Endpoint to delete a sale by saleId
    @DeleteMapping("/{saleId}")
    public ResponseEntity<Void> deleteSale(@PathVariable int saleId) {
        try {
            salesService.deleteSaleBySaleId(saleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Return status 204 if deletion was successful
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Return 404 if not found
        }
    }
}
