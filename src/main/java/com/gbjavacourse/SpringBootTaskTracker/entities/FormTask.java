package com.gbjavacourse.SpringBootTaskTracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class FormTask {
    @Size(min=4, message = "Title must be at least 4 characters")
    private String title;
    private String executer_id;
    private String owner_id;
    @Size(min=5, message = "Description must be at least 5 characters")
    private String description;
    private String status_id;
}
