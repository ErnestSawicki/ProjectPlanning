package pl.com.ErnestSawicki.ProjectPlanning.ProjectPlanning.data.model;

import javax.persistence.*;

@Entity
@Table(name = "privileges")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}
