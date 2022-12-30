package com.example.tpFinal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Time;
import java.util.List;

@Data
@Entity
@Getter @Setter
@Table(name = "sucursal")
@NoArgsConstructor
public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_sucursal")
    private long numeroSucursal;

    @Size(min = 1,max = 30)
    @NotBlank
    private String zona;
    private Time horarioApertura;
    private Time horarioCierre;


    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.PERSIST)
    private List<Cliente> clientes;

    public Sucursal(long numeroSucursal, String zona, Time horarioApertura, Time horarioCierre) {
        this.numeroSucursal = numeroSucursal;
        this.zona = zona;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }


}
