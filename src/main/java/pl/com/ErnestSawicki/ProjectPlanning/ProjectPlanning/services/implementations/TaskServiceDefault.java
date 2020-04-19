package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.implementations;

import org.springframework.stereotype.Service;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskStatus;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskType;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.TaskDTOCreate;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Service
public class TaskServiceDefault implements TaskService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskServiceDefault(ProjectRepository projectRepository, UserRepository userRepository, TaskRepository taskRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public void createTask(TaskDTOCreate taskDTOCreate, HttpServletRequest request) {
        Task taskToCreate = new Task();
        taskToCreate.setProject(projectRepository.getOne(taskDTOCreate.getPID()));
        taskToCreate.setTaskDescription(taskDTOCreate.getTaskDescription());
        taskToCreate.setStartDate(LocalDate.parse(taskDTOCreate.getStartDate()));
        taskToCreate.setDueDate(LocalDate.parse(taskDTOCreate.getDueDate()));
        taskToCreate.setPlannedHours(taskDTOCreate.getPlannedHours());
        taskToCreate.setTaskStatus(TaskStatus.valueOf(taskDTOCreate.getTaskStatus()));
        taskToCreate.setTaskType(TaskType.valueOf(taskDTOCreate.getTaskType()));

        String username = request.getUserPrincipal().getName();
        User loggedUser = userRepository.findUserByUsername(username).get(0);
        taskToCreate.setTaskOwner(loggedUser);
        taskToCreate.setOwnerId(loggedUser.getId());

        User assignee = userRepository.findUserByUsername(taskDTOCreate.getAssigneeName()).get(0);
        taskToCreate.setTaskAssignee(assignee);
        taskToCreate.setAssigneeId(loggedUser.getId());

        taskRepository.save(taskToCreate);
    }
}
