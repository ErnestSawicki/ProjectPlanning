package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskStatus;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.TaskType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter @Setter
public class Task extends EntityBase{

    @Column(columnDefinition = "TEXT", name = "description", nullable = false)
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Column(name = "project_id", insertable = false, updatable = false)
    private String projectId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    @Column(name = "planned_hours")
    private Integer plannedHours;

    @Column(name = "actual_hours")
    private Integer actualHours;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User taskOwner;
    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User taskAssignee;
    @Column(name = "assignee_id", insertable = false, updatable = false)
    private Long assigneeId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Task_Part",
    joinColumns = {@JoinColumn(name = "task_id")},
    inverseJoinColumns = {@JoinColumn(name = "part_id")})
    private List<Part> parts = new ArrayList<>();





}
