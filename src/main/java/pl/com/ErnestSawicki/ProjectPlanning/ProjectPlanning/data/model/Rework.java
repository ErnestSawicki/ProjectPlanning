package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model.enumeration.ReworkCause;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "reworks")
@Getter @Setter @ToString
public class Rework extends EntityBase {

    @Column(name = "description")
    private String description;

    @Column(name = "reworkCause")
    private ReworkCause reworkCause;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "finishDate")
    private LocalDate finishDate;

    @Column(name = "hoursPlanned")
    private Integer hoursPlanned;

    @Column(name = "hoursSpent")
    private Integer hoursSpent;

}
