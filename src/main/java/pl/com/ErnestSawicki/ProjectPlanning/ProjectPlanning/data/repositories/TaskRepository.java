package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTaskOwnerUsernameOrderByStartDateDesc(String username);

}
