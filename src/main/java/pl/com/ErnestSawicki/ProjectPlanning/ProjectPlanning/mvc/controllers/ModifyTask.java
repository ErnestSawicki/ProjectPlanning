package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.TaskStatus;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.TaskType;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.Date;

@RequestMapping("/modifyTask")
@Controller
public class ModifyTask {

    private final TaskRepository taskRepository;

    @Autowired
    public ModifyTask(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String getTaskModifyPage(@RequestParam Long taskId,
                                    Model model){
        Task taskToModify = taskRepository.getOne(taskId);
        model.addAttribute("taskToModify", taskToModify);
        return "/WEB-INF/views/modify-task.jsp";
    }

    @PostMapping
    public String modifyTask(@RequestParam Long taskId,
                             @RequestParam String taskDescription,
                             @RequestParam String startDate,
                             @RequestParam String dueDate,
                             @RequestParam Integer plannedHours,
                             @RequestParam String taskType,
                             @RequestParam String taskStatus){

        Task taskToModify = taskRepository.getOne(taskId);
        taskToModify.setTaskDescription(taskDescription);
        taskToModify.setStartDate(LocalDate.parse(startDate));
        taskToModify.setEndDate(LocalDate.parse(dueDate));
        taskToModify.setPlannedHours(plannedHours);
        taskToModify.setTaskStatus(TaskStatus.valueOf(taskStatus));
        taskToModify.setTaskType(TaskType.valueOf(taskType));
        taskRepository.save(taskToModify);
        return "redirect:/userTasks";
    }
}
