package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.TradeInCredit;

import java.util.List;
import java.util.Optional;

public interface TradeInCreditRepository {
    int saveTradeInCredit(TradeInCredit tradeInCredit);
    int updateTradeInCreditByTradeId(TradeInCredit tradeInCredit);
    int deleteTradeInCreditByTradeId(int tradeId);
    Optional<TradeInCredit> findTradeInCreditByTradeId(int tradeId);
    List<TradeInCredit> findAllTradeInCredits();
}
