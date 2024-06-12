package com.group_one.todo_list.components;

import com.group_one.todo_list.models.Category;
import com.group_one.todo_list.models.Household;
import com.group_one.todo_list.models.Task;
import com.group_one.todo_list.models.User;
import com.group_one.todo_list.repositories.HouseholdRepository;
import com.group_one.todo_list.repositories.TaskRepository;
import com.group_one.todo_list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HouseholdRepository householdRepository;

    @Autowired
    TaskRepository taskRepository;


    public void run(ApplicationArguments args) throws Exception{
        Household igloo = new Household("The North Pole Igloo");
        Household blueHouse = new Household("Blue House");
        Household palace = new Household("Buckingham Palace");
        Household house = new Household("Family House");

        User ghishani = new User("Ghishani", Category.CLEANING,igloo, 23);
        User tom = new User("Tom", Category.LAUNDRY, blueHouse, 24);
        User adil = new User("Adil", Category.HOOVERING, blueHouse, 29);
        User gellila = new User("Gellila", Category.COOKING, palace, 23);
        User father = new User("John", Category.COOKING, house, 50);
        User mother = new User("Sarah", Category.CLEANING, house, 54);
        User son = new User("Bob", Category.HOOVERING, house, 14);
        User daughter = new User("Bobbie", Category.LAUNDRY, house,12);

        Task task1 = new Task("Wipe the kitchen countertops", Category.CLEANING, LocalDate.of(2024, 6, 18),blueHouse);
        Task task2 = new Task("Put the clothes in the washing machine", Category.LAUNDRY, LocalDate.of(2024, 6, 16),igloo);
        Task task3 = new Task("Hoover the living room", Category.HOOVERING, LocalDate.of(2024, 6, 15),blueHouse);
        Task task4 = new Task("Cook lunch", Category.COOKING, LocalDate.of(2024, 6, 13), palace);
        Task task5 = new Task("Cook dinner", Category.COOKING, LocalDate.of(2024, 6, 14), house);
        Task task6 = new Task("Tidy the bedrooms", Category.CLEANING, LocalDate.of(2024, 6, 12), house);
        Task task7 = new Task("Hoover up the stairs", Category.HOOVERING, LocalDate.of(2024, 6, 11), house);
        Task task8 = new Task("Put the clothes in the dryer", Category.LAUNDRY, LocalDate.of(2024, 6, 10), house);


        householdRepository.save(igloo);
        householdRepository.save(blueHouse);
        householdRepository.save(palace);
        householdRepository.save(house);

        userRepository.save(ghishani);
        userRepository.save(tom);
        userRepository.save(adil);
        userRepository.save(gellila);
        userRepository.save(father);
        userRepository.save(mother);
        userRepository.save(son);
        userRepository.save(daughter);

        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);
        taskRepository.save(task6);
        taskRepository.save(task7);
        taskRepository.save(task8);

    }
}
