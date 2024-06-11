package com.group_one.todo_list.controllers;

import com.group_one.todo_list.models.Household;
import com.group_one.todo_list.services.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/households")

public class HouseholdController {

    @Autowired
    HouseholdService householdService;

    @GetMapping //localhost:8080/households
    public ResponseEntity<List<Household>> getAllHouseholds(){
        List<Household> households = householdService.getAllHouseholds();
        return new ResponseEntity<>(households, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")//localhost:8080/households/1
    public ResponseEntity<Household> getHouseholdById(@PathVariable Long id){
        Optional<Household> foundHousehold = householdService.getHouseholdById(id);
        if (foundHousehold.isPresent()){
            return new ResponseEntity<>(foundHousehold.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    


}
