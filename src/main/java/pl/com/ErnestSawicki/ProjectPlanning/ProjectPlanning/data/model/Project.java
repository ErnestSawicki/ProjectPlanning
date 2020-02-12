package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
public class Project {

    @Id
    @Column
    private String PID;

    @Column
    private String projectName;


    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ProjectMaturity projectMaturity;

    @ManyToMany(mappedBy = "projects")
    private List<User> participants;

    @OneToMany
    private List<Task> tasks;

}
