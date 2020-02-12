package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter @Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", name = "description", nullable = false)
    private String taskDescription;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
    @Column(name = "project_id", insertable = false, updatable = false)
    private String projectId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "planned_hours")
    private Integer plannedHours;

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



}
