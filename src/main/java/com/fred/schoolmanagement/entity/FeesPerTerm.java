package com.fred.schoolmanagement.entity;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "fees_per_term")
public class FeesPerTerm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 11)
    private long Id;

    @Column(name = "term")
    private double term;

    @NotNull
    @Column(name = "start_date")
    private LocalDate termStartDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate termEndDate;

    @Column(name = "fee_charged")
    private long feeCharged;

    public FeesPerTerm() {
    }

    public FeesPerTerm(long id, double term, LocalDate termStartDate, LocalDate termEndDate, long feeCharged) {
        Id = id;
        this.term = term;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
        this.feeCharged = feeCharged;
    }

    public FeesPerTerm(double term, long feeCharged, LocalDate termEndDate, LocalDate termStartDate) {
        this.term = term;
        this.feeCharged = feeCharged;
        this.termEndDate = termEndDate;
        this.termStartDate = termStartDate;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public double getTerm() {
        return term;
    }

    public void setTerm(double term) {
        this.term = term;
    }

    public LocalDate getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(LocalDate termStartDate) {
        this.termStartDate = termStartDate;
    }

    public LocalDate getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(LocalDate termEndDate) {
        this.termEndDate = termEndDate;
    }

    public long getFeeCharged() {
        return feeCharged;
    }

    public void setFeeCharged(long feeCharged) {
        this.feeCharged = feeCharged;
    }

    @Override
    public String toString() {
        return "FeesPerTerm{" +
                "Id=" + Id +
                ", term=" + term +
                ", termStartDate=" + termStartDate +
                ", termEndDate=" + termEndDate +
                ", feeCharged=" + feeCharged +
                '}';
    }
}
