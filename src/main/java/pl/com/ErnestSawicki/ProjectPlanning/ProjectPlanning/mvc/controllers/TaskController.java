package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.*;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/createTask")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    public String getCreateTaskPage(Model model){
        List<User> assignees = userRepository.findAll();
        model.addAttribute("assignees", assignees);

        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projects", projects);
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
        task.setProjectId(PID);
        task.setProject(projectRepository.getOne(PID));
        task.setTaskDescription(taskDescription);
        task.setStartDate(LocalDate.parse(startDate));
        task.setEndDate(LocalDate.parse(dueDate));
        task.setPlannedHours(plannedHours);
        task.setTaskStatus(TaskStatus.valueOf(taskStatus));
        task.setTaskType(TaskType.valueOf(taskType));

        String username = request.getUserPrincipal().getName();
        User loggedUser = userRepository.findUserByUsername(username).get(0);
        task.setTaskOwner(loggedUser);
        task.setOwnerId(loggedUser.getId());
        task.setTaskAssignee(loggedUser);
        task.setAssigneeId(loggedUser.getId());
        taskRepository.save(task);
        return "redirect:/";
    }
}
