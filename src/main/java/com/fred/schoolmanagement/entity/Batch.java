package com.fred.schoolmanagement.entity;


import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Entity
@Table(name = "batches")
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "batch_id", length = 11)
    private long batchId;

    @NotNull
    @Column(name = "batch_name", length = 45)
    private String batchName;

    @NotNull
    @Column(name = "start_date", length = 12)
    private String startDate;

    //many batches contain one course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    //cascade for deleting all rlshps having this as a foreighn key
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Enrollment> enrollmentSet;


    public Batch() {
    }

    public Batch(long batchId, String batchName, String startDate, Course course, Set<Enrollment> enrollmentSet) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.startDate = startDate;
        this.course = course;
        this.enrollmentSet = enrollmentSet;
    }

    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Enrollment> getEnrollmentSet() {
        return enrollmentSet;
    }

    public void setEnrollmentSet(Set<Enrollment> enrollmentSet) {
        this.enrollmentSet = enrollmentSet;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchId=" + batchId +
                ", batchName='" + batchName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", course=" + course +
                ", enrollmentSet=" + enrollmentSet +
                '}';
    }
}
