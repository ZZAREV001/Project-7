package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;

import java.util.List;
import java.util.Optional;

public interface BidListService {
    List<BidList> findAll();

    void save(BidList bid);

    Optional<BidList> findById(Integer id);

    void deleteById(Integer id);
}
