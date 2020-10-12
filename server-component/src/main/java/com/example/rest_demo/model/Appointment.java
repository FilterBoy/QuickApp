package com.example.rest_demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.Objects;


@Entity
public class Appointment {
    private @Id @GeneratedValue Long id;
    private Long salesEmployeeId;
    private String address;
    private OffsetDateTime begin;
    private OffsetDateTime end;
    private String note;

    public Appointment() {}

    public Appointment(Long salesEmployeeId, String address, OffsetDateTime begin, OffsetDateTime end, String note) {
        this.salesEmployeeId = salesEmployeeId;
        this.address = address;
        this.begin = begin;
        this.end = end;
        this.note = note;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(salesEmployeeId, that.salesEmployeeId) &&
                Objects.equals(address, that.address) &&
                Objects.equals(begin, that.begin) &&
                Objects.equals(end, that.end) &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, salesEmployeeId, address, begin, end, note);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", salesEmployeeId=" + salesEmployeeId +
                ", address='" + address + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", note='" + note + '\'' +
                '}';
    }
}
