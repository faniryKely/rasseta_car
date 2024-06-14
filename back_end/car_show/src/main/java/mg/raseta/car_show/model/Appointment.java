package mg.raseta.car_show.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mg.raseta.car_show.model.enums.AppointmentStatus;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointment")
public class Appointment {

    @Id
    @Column(name = "appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointmentId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "message")
    private String message;

    @Column(name = "contact", nullable = false)
    private String contact;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonBackReference("car-appointment")
    private Car car;

}