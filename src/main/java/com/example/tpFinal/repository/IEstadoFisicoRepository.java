package com.example.tpFinal.repository;

import com.example.tpFinal.model.EstadoFisico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEstadoFisicoRepository extends JpaRepository<EstadoFisico, Long> {

    @Query(value = "FROM EstadoFisico")
    List<EstadoFisico> findAllEF();
/*
    @Query(value = "FROM EstadoFisico E WHERE E.id LIKE :id")
    EstadoFisico findByN(@Param("id")Long id);


 */
}
