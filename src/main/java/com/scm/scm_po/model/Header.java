package com.scm.scm_po.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tm_header")
@Data
public class Header {
    @Id
    private String poNumber;
    private LocalDate poDate;
    private String buyerName;
    private String buyerAddr;

    @OneToMany(mappedBy = "header", cascade = CascadeType.ALL)
    private List<Detail> details;

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public LocalDate getPoDate() {
        return poDate;
    }

    public void setPoDate(LocalDate poDate) {
        this.poDate = poDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddr() {
        return buyerAddr;
    }

    public void setBuyerAddr(String buyerAddr) {
        this.buyerAddr = buyerAddr;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }
}
