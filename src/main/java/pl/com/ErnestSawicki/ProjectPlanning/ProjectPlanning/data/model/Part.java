package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "parts")
@Getter @Setter
public class Part extends EntityBase{

    private String name;

    private String partNumber;

    @ManyToMany(mappedBy = "parts")
    private List<Task> tasks = new ArrayList<>();

}
