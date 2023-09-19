package al.adela.classroomreservation.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ADMINISTRATOR")
public class Administrator {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

}
