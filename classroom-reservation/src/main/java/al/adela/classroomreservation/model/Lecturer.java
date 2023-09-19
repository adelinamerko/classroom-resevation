package al.adela.classroomreservation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "LECTURER")
public class Lecturer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToMany(mappedBy = "lecturer", fetch = FetchType.EAGER)
    @JsonManagedReference
    @ToString.Exclude
    private List<Classroom> classroomList;

}
