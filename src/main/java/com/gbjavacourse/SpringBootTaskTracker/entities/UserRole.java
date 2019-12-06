package com.gbjavacourse.SpringBootTaskTracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="userroles")
@Data
@NoArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "rolename")
    private String rolename;
/*
    @ManyToMany
    @JoinTable(
            name = "users_userroles",
            joinColumns = @JoinColumn(name = "userroles_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<User> users;
*/
}