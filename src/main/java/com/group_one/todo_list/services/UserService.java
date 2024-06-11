package com.group_one.todo_list.services;

import com.group_one.todo_list.models.*;
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

    public User updateUser(long id, UserDTO userDTO) {
        Optional<User> userToUpdate = userRepository.findById(id);

        User updatedUser = userToUpdate.get();

        if (userDTO.getName() != null) {
            updatedUser.setName(userDTO.getName());
        }

        if (userDTO.getPreference() != null) {
            updatedUser.setPreference(userDTO.getPreference());
        }

        if (userDTO.getHouseholdId() != 0) {
            Optional<Household> householdOptional = householdRepository.findById(userDTO.getHouseholdId());
            if (householdOptional.isPresent()) {
                updatedUser.setHousehold(householdOptional.get());
            } else {
                return userRepository.save(updatedUser); // this means if a user household doesn't exist it won't change but every other element will.
            }
        }

        return userRepository.save(updatedUser);

    }
}
