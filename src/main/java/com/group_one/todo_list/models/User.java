package com.group_one.todo_list.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "preference")
    private Category preference;

//    TODO: JsonIgnore
    @ManyToOne
    @JoinColumn(name = "household_id")
    @JsonIgnoreProperties({"users"})
    private Household household;

//    TODO: JsonIgnore
    @OneToMany(mappedBy = "user")// the @JoinColumn is on the other side of the OneToMany relationship
    @JsonIgnoreProperties({"user", "household"})
    private List<Task> tasks;

    public User(String name, Category preference, Household household) {
        this.name = name;
        this.preference = preference;
        this.household = household;
        this.tasks = new ArrayList<>();
    }

    public User(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getPreference() {
        return preference;
    }

    public void setPreference(Category preference) {
        this.preference = preference;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public void removeTask(Task task) {
        this.tasks.remove(task);
    }
}
