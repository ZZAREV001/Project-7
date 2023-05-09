package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;

    @Override
    public List<Trade> findAll() {
        return tradeRepository.findAll();
    }

    @Override
    public void save(Trade trade) {
        tradeRepository.save(trade);
    }

    @Override
    public Optional<Trade> findById(Integer id) {
        return tradeRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        tradeRepository.deleteById(id);
    }
}
