package com.example.tpFinal.repository;

import com.example.tpFinal.model.Cliente;
import com.example.tpFinal.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Long> {

    @Query("FROM Sucursal")
    List<Sucursal> mostrarSucursales();

    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE :name")
    Cliente findByName(@Param("name")String name);

}
