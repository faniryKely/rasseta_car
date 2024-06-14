package mg.raseta.car_show.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car_types")
public class CarTypes {

    @Id
    @Column(name = "car_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carTypeId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "carTypes")
    @JsonManagedReference("carTypes-car")
    private List<Car> cars;

}