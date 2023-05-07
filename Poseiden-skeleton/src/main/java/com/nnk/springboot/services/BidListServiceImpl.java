package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;
    @Override
    public List<BidList> findAll() {
        return bidListRepository.findAll();
    }

    @Override
    public void save(BidList bid) {
        bidListRepository.save(bid);
    }

    @Override
    public Optional<BidList> findById(Integer id) {
        return bidListRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        bidListRepository.deleteById(id);
    }

}
