package com.gbjavacourse.SpringBootTaskTracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "executer_id")
    private User executer;
    @Column(name = "description")
    private String description;
   /* @Column(name = "status")
    private String status;*/
    @ManyToOne
    @JoinColumn(name = "status_id")
    private TaskStatus status;

    public Task(String title, User owner, User executer, String description, TaskStatus status) {

        this.title = title;
        this.owner = owner;
        this.executer = executer;
        this.description = description;
        this.status = status;
    }

    public void printTask(){
        System.out.println(id + ".  " + title + ". " + executer.toString() + ". " + (status==null?"":status.toString()) );
    }

    public String getTaskInfo(){
        return id + ".  " + title + ".  " +(owner==null?"":owner.toString()) +  ".  " +executer.toString()+ ".  " + (description==null?"":description) + ".  " + (status==null?"":status.toString());
    }

    @Override
    public String toString() {
        return id + ".  " + title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(owner, task.owner) &&
                Objects.equals(executer, task.executer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, owner, executer, description, status);
    }
}

