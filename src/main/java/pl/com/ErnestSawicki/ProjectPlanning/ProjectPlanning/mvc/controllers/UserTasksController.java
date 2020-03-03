package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskStatus;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskType;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.specification.TaskSpecification;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/userTasks")
@Slf4j
public class UserTasksController {

    private final TaskRepository taskRepository;

    @Autowired
    public UserTasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String getUserTasksPage(Principal principal, Model model){
        List<TaskStatus> taskStatuses = Arrays.asList(TaskStatus.values());
        model.addAttribute("taskStatuses", taskStatuses);
        List<TaskType> taskTypes = Arrays.asList(TaskType.values());
        model.addAttribute("taskTypes", taskTypes);

        String username = principal.getName();
        List<Task> userTasks = taskRepository.findAllByTaskOwnerUsernameOrderByStartDateDesc(username);
        model.addAttribute("userTasks", userTasks);
        return "user-tasks";
    }

    @PostMapping()
    public String userTasksStatusFiltered(@RequestParam String taskStatus,
                                          @RequestParam String taskType,
                                          Model model){
        Task taskCriteria = new Task();
        if (!taskStatus.equals("ALL")){
            taskCriteria.setTaskStatus(TaskStatus.valueOf(taskStatus));
        }
        if (!taskType.equals("ALL")){
            taskCriteria.setTaskType(TaskType.valueOf(taskType));
        }
        Specification<Task> specification = new TaskSpecification(taskCriteria);
        List<Task> userTasksFiltered = taskRepository.findAll(specification);

        model.addAttribute("userTasks", userTasksFiltered);

        List<TaskStatus> taskStatuses = Arrays.asList(TaskStatus.values());
        model.addAttribute("taskStatuses", taskStatuses);
        List<TaskType> taskTypes = Arrays.asList(TaskType.values());
        model.addAttribute("taskTypes", taskTypes);
        return "user-tasks";
    }
}
