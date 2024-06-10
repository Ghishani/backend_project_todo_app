package com.group_one.todo_list.models;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "preference")
    private String preference;

//    TODO: JsonIgnore
    @ManyToOne
    @JoinColumn(name = "household_id")
    private Household household;

//    TODO: JsonIgnore
    @OneToMany(mappedBy = "task")// the @JoinColumn is on the other side of the OneToMany relationship
    private ArrayList<Task> tasks;

    public User(String name, String preference, Household household) {
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

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

//    TODO: create methods to add and remove tasks from tasks ArrayList

//    public void addTask(Task newTask) {
//        this.tasks.add(newTask);
//    }
//
//    public Task removeTask()
}
