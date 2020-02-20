package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Project;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
    List<Task> findAllByTaskOwnerUsernameOrderByStartDateDesc(String username);
    List<Task> findAllByTaskOwnerUsernameAndTaskStatus(String username, TaskStatus taskType);
    List<Task> findAllByProject(Project project);

}
