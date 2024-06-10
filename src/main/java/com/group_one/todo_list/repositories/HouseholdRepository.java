package com.group_one.todo_list.repositories;

import com.group_one.todo_list.models.Household;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
}
