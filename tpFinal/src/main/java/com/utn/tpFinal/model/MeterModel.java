package com.utn.tpFinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="meter_models")
public class MeterModel {


    @Id
    private String name;

    @OneToMany(mappedBy = "model")
    @JsonIgnore
    private List<EnergyMeter> energyMeters;

    @Override
    public String toString() {
        return "MeterModel{" +
                ", name='" + name + '\'' +
                '}';
    }
}
