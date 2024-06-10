package com.group_one.todo_list.components;

import com.group_one.todo_list.models.Household;
import com.group_one.todo_list.models.Task;
import com.group_one.todo_list.models.User;
import com.group_one.todo_list.repositories.HouseholdRepository;
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


    public void run(ApplicationArguments args) throws Exception{
        Household igloo = new Household("The North Pole Igloo");
        Household blueHouse = new Household("Blue House");
        Household palace = new Household("Buckingham Palace");
        Household house = new Household("Family House");

        User ghishani = new User("Ghishani", "Cleaning",igloo);
        User tom = new User("Tom", "Laundry", blueHouse);
        User adil = new User("Adil", "Hoovering", blueHouse);
        User gellila = new User("Gellila", "Cooking", palace);
        User father = new User("John", "Cooking", house);
        User mother = new User("Sarah", "Cleaning", house);
        User son = new User("Bob", "Hoovering", house);
        User daughter = new User("Bobbie", "Laundry", house);

        Task task1 = new Task("Wipe the kitchen countertops", "Cleaning", LocalDate.of(2024, 6, 18),blueHouse);
        Task task2 = new Task("Put the clothes in the washing machine", "Laundry", LocalDate.of(2024, 6, 16),igloo);
        Task task3 = new Task("Hoover the living room", "Hoovering", LocalDate.of(2024, 6, 15),blueHouse);
        Task task4 = new Task("Cook lunch", "Cooking", LocalDate.of(2024, 6, 13), palace);
        Task task5 = new Task("Cook dinner", "Cooking", LocalDate.of(2024, 6, 14), house);
        Task task6 = new Task("Tidy the bedrooms", "Cleaning", LocalDate.of(2024, 6, 12), house);
        Task task7 = new Task("Hoover up the stairs", "Hoovering", LocalDate.of(2024, 6, 11), house);
        Task task8 = new Task("Put the clothes in the dryer", "Laundry", LocalDate.of(2024, 6, 10), house);


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

    }
}
