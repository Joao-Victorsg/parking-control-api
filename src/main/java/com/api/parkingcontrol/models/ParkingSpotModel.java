package com.api.parkingcontrol.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@Table(name = "TB_PARKINGSPOTMODEL")
public class ParkingSpotModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carmodel_id", unique = true)
    private CarModel carModel;
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsiblemodel_id", unique = true)
    private ResponsibleModel responsibleModel;

}
