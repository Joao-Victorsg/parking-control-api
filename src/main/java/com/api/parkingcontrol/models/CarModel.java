package com.api.parkingcontrol.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_CARMODEL")
@Getter
@Setter
public class CarModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true, length = 7)
    private String licensePlateCar;
    @Column(nullable = false,length = 70)
    private String brandCar;
    @Column(nullable = false,length = 70)
    private String modelCar;
    @Column(nullable = false,length = 70)
    private String colorCar;
    @OneToOne(mappedBy = "carModel")
    private ParkingSpotModel parkingSpotModel;

}
