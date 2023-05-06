package com.nnk.springboot.domain;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    private int id;

    private Integer curveId;

    private Timestamp asOfDate;

    private double term;

    private double value;

    private Timestamp creationDate;

    public CurvePoint(int i, double v, double v1) {
    }
}
