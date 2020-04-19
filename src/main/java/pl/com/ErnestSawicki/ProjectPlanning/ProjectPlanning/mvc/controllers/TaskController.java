package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.*;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskStatus;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskType;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.PartRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.ProjectRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.UserRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.TaskDTOCreate;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.TaskDTOModify;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final PartRepository partRepository;
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository, PartRepository partRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.partRepository = partRepository;
        this.taskService = taskService;
    }

    @GetMapping(value = "/createTask")
    public String getCreateTaskPage(Model model, Principal principal){
        List<User> assignees = userRepository.findAll();
        model.addAttribute("assignees", assignees);

        User loggedUser = userRepository.findUserByUsername(principal.getName()).get(0);
        List<Project> projects = projectRepository.findAllByParticipantsIs(loggedUser);
        model.addAttribute("projects", projects);
        return "create-task";
    }

    @PostMapping(value = "/createTask")
    public String createTask(TaskDTOCreate taskDTOCreate,
                             HttpServletRequest request){
        taskService.createTask(taskDTOCreate, request);
        return "redirect:/";
    }

    @GetMapping(value = "/modifyTask")
    public String getTaskModifyPage(@RequestParam Long taskId,
                                    Model model){
        Task taskToModify = taskRepository.getOne(taskId);
        model.addAttribute("taskToModify", taskToModify);
        return "modify-task";
    }

    @PostMapping(value = "/modifyTask")
    public String modifyTask(@RequestParam Long taskId, TaskDTOModify taskDTOModify){
        Task taskToModify = taskRepository.getOne(taskId);
        taskDTOModify.copyProperties(taskDTOModify, taskToModify);
        taskRepository.save(taskToModify);
        return "redirect:/userTasks";
    }
}
