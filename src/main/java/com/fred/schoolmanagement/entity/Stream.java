package com.fred.schoolmanagement.entity;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "streams")
public class Stream {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stream_id")
    private long streamId;

    @Column(name = "stream_code")
    private long streamCode;

    @Column(name = "stream_name")
    private String streamName;

    @OneToMany(mappedBy = "stream", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Student> students ;

    public Stream(){

    }

    public Stream(long streamId, long streamCode, String streamName, Set<Student> students) {
        this.streamId = streamId;
        this.streamCode = streamCode;
        this.streamName = streamName;
        this.students = students;
    }

    public long getStreamId() {
        return streamId;
    }

    public void setStreamId(long streamId) {
        this.streamId = streamId;
    }

    public long getStreamCode() {
        return streamCode;
    }

    public void setStreamCode(long streamCode) {
        this.streamCode = streamCode;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "streamId=" + streamId +
                ", streamCode=" + streamCode +
                ", streamName='" + streamName + '\'' +
                ", students=" + students +
                '}';
    }
}
