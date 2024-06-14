package mg.raseta.car_show.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "motor_types")
public class MotorTypes {

    @Id
    @Column(name = "motor_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int motorTypeId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "motorTypes")
    @JsonBackReference("motorTypes-car")
    private List<Car> cars;

}