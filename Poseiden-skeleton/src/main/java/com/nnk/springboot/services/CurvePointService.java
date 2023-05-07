package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import java.util.List;
import java.util.Optional;

public interface CurvePointService {

    CurvePoint saveCurvePoint(CurvePoint curvePoint);

    List<CurvePoint> findAll();

    Optional<CurvePoint> findById(Integer id);

    CurvePoint updateCurvePoint(CurvePoint curvePoint);

    void deleteCurvePointById(Integer id);
}
