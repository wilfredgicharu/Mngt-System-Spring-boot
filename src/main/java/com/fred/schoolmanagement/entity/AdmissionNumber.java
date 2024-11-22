package com.fred.schoolmanagement.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "admission_number_tracker")

public class AdmissionNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admission_number_id")
    private long admissionNumberId;

    @Nullable
    @Column(name = "recent_admission_number", unique = true)
    private String recentAdmissionNumber;

    public AdmissionNumber() {
    }

    public AdmissionNumber(long admissionNumberId, String recentAdmissionNumber) {
        this.admissionNumberId = admissionNumberId;
        this.recentAdmissionNumber = recentAdmissionNumber;
    }

    public long getAdmissionNumberId() {
        return admissionNumberId;
    }

    public void setAdmissionNumberId(long admissionNumberId) {
        this.admissionNumberId = admissionNumberId;
    }

    public String getRecentAdmissionNumber() {
        return recentAdmissionNumber;
    }

    public void setRecentAdmissionNumber(String recentAdmissionNumber) {
        this.recentAdmissionNumber = recentAdmissionNumber;
    }

    @Override
    public String toString() {
        return "AdmissionNumber{" +
                "admissionNumberId=" + admissionNumberId +
                ", recentAdmissionNumber='" + recentAdmissionNumber + '\'' +
                '}';
    }
}
