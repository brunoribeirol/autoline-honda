package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.TradeInCredit;
import com.cesarschool.autoline_honda.repository.TradeInCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TradeInCreditService {

    private final TradeInCreditRepository tradeInCreditRepository;

    @Autowired
    public TradeInCreditService(TradeInCreditRepository tradeInCreditRepository) {
        this.tradeInCreditRepository = tradeInCreditRepository;
    }

    public TradeInCredit createTradeInCredit(TradeInCredit tradeInCredit) {
        tradeInCreditRepository.saveTradeInCredit(tradeInCredit);
        return tradeInCredit;
    }

    public TradeInCredit updateTradeInCredit(TradeInCredit tradeInCredit) {
        tradeInCreditRepository.updateTradeInCreditByTradeId(tradeInCredit);
        return tradeInCredit;
    }

    public void deleteTradeInCredit(int tradeId) {
        tradeInCreditRepository.deleteTradeInCreditByTradeId(tradeId);
    }

    public Optional<TradeInCredit> getTradeInCreditById(int tradeId) {
        return tradeInCreditRepository.findTradeInCreditByTradeId(tradeId);
    }

    public List<TradeInCredit> getAllTradeInCredits() {
        return tradeInCreditRepository.findAllTradeInCredits();
    }
}
