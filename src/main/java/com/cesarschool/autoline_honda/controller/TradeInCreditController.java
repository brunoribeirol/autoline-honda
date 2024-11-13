package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.TradeInCredit;
import com.cesarschool.autoline_honda.service.TradeInCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trade-in-credits")
public class TradeInCreditController {

    private final TradeInCreditService tradeInCreditService;

    @Autowired
    public TradeInCreditController(TradeInCreditService tradeInCreditService) {
        this.tradeInCreditService = tradeInCreditService;
    }

    @PostMapping
    public ResponseEntity<TradeInCredit> createTradeInCredit(@RequestBody TradeInCredit tradeInCredit) {
        try {
            TradeInCredit createdTradeInCredit = tradeInCreditService.createTradeInCredit(tradeInCredit);
            return new ResponseEntity<>(createdTradeInCredit, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{tradeId}")
    public ResponseEntity<TradeInCredit> updateTradeInCredit(@PathVariable int tradeId, @RequestBody TradeInCredit tradeInCredit) {
        try {
            tradeInCredit.setTradeId(tradeId);
            TradeInCredit updatedTradeInCredit = tradeInCreditService.updateTradeInCredit(tradeInCredit);
            return new ResponseEntity<>(updatedTradeInCredit, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tradeId}")
    public ResponseEntity<Void> deleteTradeInCredit(@PathVariable int tradeId) {
        try {
            tradeInCreditService.deleteTradeInCredit(tradeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<TradeInCredit> getTradeInCreditById(@PathVariable int tradeId) {
        Optional<TradeInCredit> tradeInCredit = tradeInCreditService.getTradeInCreditById(tradeId);
        return tradeInCredit.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<TradeInCredit>> getAllTradeInCredits() {
        List<TradeInCredit> tradeInCredits = tradeInCreditService.getAllTradeInCredits();
        return new ResponseEntity<>(tradeInCredits, HttpStatus.OK);
    }
}
