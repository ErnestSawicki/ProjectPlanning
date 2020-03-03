package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.ProjectMaturity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@Table(name = "projects")
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
    private List<User> participants = new ArrayList<>();

    @OneToMany
    private List<Task> tasks = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(PID, project.PID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PID);
    }

    @Override
    public String toString() {
        return "Project{" +
                "PID='" + PID + '\'' +
                ", projectName='" + projectName + '\'' +
                ", description='" + description + '\'' +
                ", projectMaturity=" + projectMaturity;
    }
}
