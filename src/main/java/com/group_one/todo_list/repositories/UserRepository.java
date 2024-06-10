package com.group_one.todo_list.repositories;

import com.group_one.todo_list.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
