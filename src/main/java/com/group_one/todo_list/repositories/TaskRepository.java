package com.group_one.todo_list.repositories;

import com.group_one.todo_list.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
