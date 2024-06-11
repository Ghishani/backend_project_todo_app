package com.group_one.todo_list.services;

import com.group_one.todo_list.models.Household;
import com.group_one.todo_list.models.User;
import com.group_one.todo_list.models.UserDTO;
import com.group_one.todo_list.repositories.HouseholdRepository;
import com.group_one.todo_list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HouseholdRepository householdRepository;

    @Autowired
    HouseholdService householdService;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id){
        return userRepository.findById(id);
    }

    public User createUser(UserDTO userDTO){
        Optional<Household> household = householdRepository.findById(userDTO.getHouseholdId());

        if(household.isPresent()){
            User newUser = new User(
                    userDTO.getName(),
                    userDTO.getPreference(),
                    household.get());
            return userRepository.save(newUser);

        }
        return null;
    }

}
