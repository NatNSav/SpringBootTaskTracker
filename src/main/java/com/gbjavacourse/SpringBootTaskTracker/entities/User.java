package com.gbjavacourse.SpringBootTaskTracker.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "middlename")
    private String middlename;

    @OneToMany(mappedBy = "owner")
    List<Task> owner_tasks;

    @OneToMany(mappedBy = "executer")
    List<Task> executer_tasks;
   /* @ManyToMany
    @JoinTable(
            name = "users_userroles",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "userroles_id")
    )
    private List<UserRole> userroles;*/

    public User(String name, String surname, String middlename) {
        this.name = name;
        this.surname = surname;
        this.middlename = middlename;
    }

    public void printUser(){
        System.out.println(toString() );
    }

    public String getUserInfo(){
        return toString();//+ " " + Arrays.toString(userroles.toArray());
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + middlename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(name, user.name) &&
                Objects.equals(middlename, user.middlename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, middlename);
    }
}

