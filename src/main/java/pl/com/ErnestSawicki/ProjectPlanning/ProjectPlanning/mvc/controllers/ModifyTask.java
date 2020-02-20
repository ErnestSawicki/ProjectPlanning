package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.mvc.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.TaskStatus;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.TaskType;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.repositories.TaskRepository;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto.TaskDTOModify;

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
        return "modify-task";
    }

    @PostMapping
    public String modifyTask(@RequestParam Long taskId, TaskDTOModify taskDTOModify){

        Task taskToModify = taskRepository.getOne(taskId);
        taskDTOModify.copyProperties(taskDTOModify, taskToModify);
        taskRepository.save(taskToModify);
        return "redirect:/userTasks";
    }
}
