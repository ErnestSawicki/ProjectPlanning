package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TaskDTOCreate {

    private String PID;

    private String taskDescription;

    private String partNumber;

    private String startDate;

    private String dueDate;

    private Integer plannedHours;

    private String taskStatus;

    private String taskType;

    private String assigneeName;
}
