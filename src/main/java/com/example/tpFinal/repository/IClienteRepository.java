package com.example.tpFinal.repository;

import com.example.tpFinal.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente,Long> {

    @Query("FROM Cliente c  WHERE c.id = :id")
    Optional<Cliente> findById(@Param("id") Long id);
}
