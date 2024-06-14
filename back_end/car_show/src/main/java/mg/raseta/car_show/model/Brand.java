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
@Table(name = "brand")
public class Brand {

    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "logo_url")
    private String logoUrl;

    @OneToMany(mappedBy = "brand")
    @JsonManagedReference("brand-car")
    private List<Car> cars;

}