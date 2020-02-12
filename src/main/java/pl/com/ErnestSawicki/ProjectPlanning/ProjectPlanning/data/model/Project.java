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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String projectName;

    @Column
    private String PID;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ProjectMaturity projectMaturity;

    @ManyToMany(mappedBy = "projects")
    private List<User> participants;

}
