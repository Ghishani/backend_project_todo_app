package com.group_one.todo_list.services;

import com.group_one.todo_list.models.Household;
import com.group_one.todo_list.repositories.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseholdService {

    @Autowired
    HouseholdRepository householdRepository;

    public List<Household> getAllHouseholds(){
        return householdRepository.findAll();
    }

    public Optional<Household> getHouseholdById(long id){
        return householdRepository.findById(id);
    }

    public Household updateHousehold(long id, Household household){
        Household householdToUpdate = householdRepository.findById(id).get();

        if(household.getName() != null){
            householdToUpdate.setName(household.getName());
        }
        return householdRepository.save(householdToUpdate);
    }

    public Household createHousehold (Household household){
        return householdRepository.save(household);
    }
}
