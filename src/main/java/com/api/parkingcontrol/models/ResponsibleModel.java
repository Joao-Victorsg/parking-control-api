package com.api.parkingcontrol.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@Table(name = "TB_RESPONSIBLEMODEL")
@NoArgsConstructor
public class ResponsibleModel implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 130)
    private String responsibleName;
    @Column(nullable = false, length = 30)
    private String apartment;
    @Column(nullable = false, length = 30)
    private String block;

    public ResponsibleModel(String responsibleName, String apartment, String block) {
        this.responsibleName = responsibleName;
        this.apartment = apartment;
        this.block = block;
    }
}
