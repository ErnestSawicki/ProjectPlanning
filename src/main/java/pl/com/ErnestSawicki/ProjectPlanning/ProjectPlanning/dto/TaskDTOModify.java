package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.Task;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.TaskStatus;

import java.time.LocalDate;

@Getter @Setter
public class TaskDTOModify {

    private String taskDescription;
    private String startDate;
    private String dueDate;
    private String finishDate;
    private Integer plannedHours;
    private Integer actualHours;
    private String taskType;
    private String taskStatus;

    public void copyProperties(TaskDTOModify source, Task target){
        target.setTaskDescription(source.taskDescription);
        target.setFinishDate(LocalDate.parse(source.finishDate));
        target.setActualHours(source.actualHours);
        target.setTaskStatus(TaskStatus.valueOf(source.taskStatus));
    }
}
