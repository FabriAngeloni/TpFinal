package com.example.tpFinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Clientes")
public class Cliente {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ClienteId")
    private Long id;

    private String nombre;
    private String apellido;
    private int edad;
    private Long dni;

    @OneToOne
    @JoinColumn(name = "estado_fisico_id",referencedColumnName = "EstadoFisicoId")
    private  EstadoFisico estadoFisico;

    @ManyToOne
    @JoinColumn(name = "numero_sucursal_id", nullable = false)
    private Sucursal sucursal;

    public Cliente(String nombre, String apellido, int edad, long dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
    }

    public Cliente(Long id, String nombre, String apellido, int edad, long dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = dni;
    }
}
