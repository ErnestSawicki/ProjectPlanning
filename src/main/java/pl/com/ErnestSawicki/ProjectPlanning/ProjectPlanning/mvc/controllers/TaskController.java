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
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.PartRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.TaskDTOCreate;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/createTask")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PartRepository partRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository, PartRepository partRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.partRepository = partRepository;
    }

    @GetMapping
    public String getCreateTaskPage(Model model, Principal principal){
        List<User> assignees = userRepository.findAll();
        model.addAttribute("assignees", assignees);

        User loggedUser = userRepository.findUserByUsername(principal.getName()).get(0);
        List<Project> projects = projectRepository.findAllByParticipantsIs(loggedUser);
        model.addAttribute("projects", projects);
        return "create-task";
    }

    @PostMapping
    public String createTask(TaskDTOCreate taskDTO,
                             HttpServletRequest request){
        Task taskToCreate = new Task();
        taskToCreate.setProject(projectRepository.getOne(taskDTO.getPID()));
        taskToCreate.setTaskDescription(taskDTO.getTaskDescription());
        taskToCreate.setStartDate(LocalDate.parse(taskDTO.getStartDate()));
        taskToCreate.setEndDate(LocalDate.parse(taskDTO.getDueDate()));
        taskToCreate.setPlannedHours(taskDTO.getPlannedHours());
        taskToCreate.setTaskStatus(TaskStatus.valueOf(taskDTO.getTaskStatus()));
        taskToCreate.setTaskType(TaskType.valueOf(taskDTO.getTaskType()));

        String username = request.getUserPrincipal().getName();
        User loggedUser = userRepository.findUserByUsername(username).get(0);
        taskToCreate.setTaskOwner(loggedUser);
        taskToCreate.setOwnerId(loggedUser.getId());

        User assignee = userRepository.findUserByUsername(taskDTO.getAssigneeName()).get(0);
        taskToCreate.setTaskAssignee(assignee);
        taskToCreate.setAssigneeId(loggedUser.getId());

        taskRepository.save(taskToCreate);
        return "redirect:/";
    }
}
