package com.example.rest_demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class SalesAvailable {
    private @Id @GeneratedValue Long id;
    private Long salesEmployeeId;
    private OffsetDateTime begin;
    private OffsetDateTime end;

    public SalesAvailable() {
    }

    public SalesAvailable(Long salesEmployeeId, OffsetDateTime begin, OffsetDateTime end) {
        this.salesEmployeeId = salesEmployeeId;
        this.begin = begin;
        this.end = end;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSalesEmployeeId() {
        return salesEmployeeId;
    }

    public void setSalesEmployeeId(Long salesEmployeeId) {
        this.salesEmployeeId = salesEmployeeId;
    }

    public OffsetDateTime getBegin() {
        return begin;
    }

    public void setBegin(OffsetDateTime begin) {
        this.begin = begin;
    }

    public OffsetDateTime getEnd() {
        return end;
    }

    public void setEnd(OffsetDateTime end) {
        this.end = end;
    }
}
