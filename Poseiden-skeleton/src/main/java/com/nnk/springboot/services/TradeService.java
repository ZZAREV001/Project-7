package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeService {
    List<Trade> findAll();

    void save(Trade trade);

    Optional<Trade> findById(Integer id);

    void deleteById(Integer id);
}
