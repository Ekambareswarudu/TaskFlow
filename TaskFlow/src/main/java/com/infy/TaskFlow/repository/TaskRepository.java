package com.infy.TaskFlow.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.infy.TaskFlow.entity.Task;
import com.infy.TaskFlow.entity.User;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);

}
