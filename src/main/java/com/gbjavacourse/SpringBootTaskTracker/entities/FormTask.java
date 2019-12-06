package com.gbjavacourse.SpringBootTaskTracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormTask {
    private String title;
    private String executer_id;
    private String owner_id;
    private String description;
    private String status_id;
}
