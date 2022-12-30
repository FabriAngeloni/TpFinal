package com.example.tpFinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "EstadoFisico")
public class EstadoFisico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EstadoFisicoId")
    private Long id;
    private double altura;
    private double peso;
    private double imc;

    @OneToOne(mappedBy = "estadoFisico")
    private Cliente cliente;


    public EstadoFisico(Long id, double altura, double peso, double imc) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.imc = imc;
    }
}
