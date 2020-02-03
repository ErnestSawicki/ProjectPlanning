package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter @Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", name = "description", nullable = false)
    private String taskDescription;

    @Column(name = "PID")
    private String PID;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "planned_hours")
    private Integer plannedHours;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User taskOwner;
    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long ownerId;

}
