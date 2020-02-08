package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.TaskStatus;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.TaskType;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.User;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/createTask")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getCreateTaskPage(){
        return "/WEB-INF/views/create-task.jsp";
    }

    @PostMapping
    public String createTask(@RequestParam String PID,
                             @RequestParam String taskDescription,
                             @RequestParam String startDate,
                             @RequestParam String dueDate,
                             @RequestParam Integer plannedHours,
                             @RequestParam String taskType,
                             @RequestParam String taskStatus,
                             HttpServletRequest request){
        Task task = new Task();
        task.setPID(PID);
        task.setTaskDescription(taskDescription);
        task.setStartDate(LocalDate.parse(startDate));
        task.setEndDate(LocalDate.parse(dueDate));
        task.setPlannedHours(plannedHours);
        task.setTaskStatus(TaskStatus.valueOf(taskStatus));
        task.setTaskType(TaskType.valueOf(taskType));
        String username = request.getUserPrincipal().getName();
        User loggedUser = userRepository.findUserByUsername(username).get(0);
        task.setTaskOwner(loggedUser);
        taskRepository.save(task);
        return "redirect:/";
    }
}
