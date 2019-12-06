package com.gbjavacourse.SpringBootTaskTracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="taskstatuses")
@Data
@NoArgsConstructor
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "taskstatus")
    private String taskstatus;

    @OneToMany(mappedBy = "status")
    List<Task> tasks;

    @Override
    public String toString() {
        return taskstatus;
    }
}