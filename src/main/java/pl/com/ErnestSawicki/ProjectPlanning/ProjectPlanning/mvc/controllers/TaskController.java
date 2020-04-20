package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
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
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.specification.TaskSpecification;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.task.TaskDTOCreate;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.task.TaskDTOModify;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Arrays;
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
        return "task/task-create";
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
        return "task/task-modify";
    }

    @PostMapping(value = "/modifyTask")
    public String modifyTask(@RequestParam Long taskId, TaskDTOModify taskDTOModify){
        Task taskToModify = taskRepository.getOne(taskId);
        taskDTOModify.copyProperties(taskDTOModify, taskToModify);
        taskRepository.save(taskToModify);
        return "redirect:/userTasks";
    }

    @GetMapping(value = "/userTasks")
    public String getUserTasksPage(Principal principal, Model model){
        List<TaskStatus> taskStatuses = Arrays.asList(TaskStatus.values());
        model.addAttribute("taskStatuses", taskStatuses);
        List<TaskType> taskTypes = Arrays.asList(TaskType.values());
        model.addAttribute("taskTypes", taskTypes);

        String username = principal.getName();
        List<Task> userTasks = taskRepository.findAllByTaskOwnerUsernameOrderByStartDateDesc(username);
        model.addAttribute("userTasks", userTasks);
        return "task/task-user";
    }

    @PostMapping(value = "/userTasks")
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
        return "task/task-user";
    }
}
