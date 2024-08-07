package com.teambridge.repository;

import com.teambridge.entity.Project;
import com.teambridge.entity.Task;
import com.teambridge.entity.User;
import com.teambridge.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.taskStatus <> 'COMPLETED'")
    int totalNonCompletedTasks(String projectCode);

    @Query(value =
            "SELECT COUNT(*) " +
            "FROM tasks t JOIN projects p " +
            "ON t.project_id = p.id " +
            "WHERE p.project_code = ?1 " +
            "AND t.task_status = 'COMPLETED'",
            nativeQuery = true)
    int totalCompletedTasks(String projectCode);

    List<Task> findAllByProject(Project project);

    List<Task> findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User employee);

    List<Task> findAllByTaskStatusAndAssignedEmployee(Status status, User employee);
}
