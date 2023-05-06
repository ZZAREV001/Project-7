package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CurvePointServiceImpl implements CurvePointService {

    private final CurvePointRepository curvePointRepository;
    @Override
    public List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }
}
