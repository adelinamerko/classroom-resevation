package al.adela.classroomreservation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CLASSROOM")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;

    @Column
    private int classroomNumber;

    @Column
    private int seatsNumber;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "LECTURER_ID")
    @ToString.Exclude
    private Lecturer lecturer;

}
