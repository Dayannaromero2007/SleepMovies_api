package com.cinesleepmovies.sleepmovies.repository;

import com.cinesleepmovies.sleepmovies.model.Compras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComprasRepository extends JpaRepository<Compras, Integer> {

    // Compras con fecha desde "inicio" (incluida) hasta "fin" (excluida), de la más reciente a la más antigua
    @Query("SELECT c FROM Compras c WHERE c.fechaCompra >= :inicio AND c.fechaCompra < :fin ORDER BY c.fechaCompra DESC")
    List<Compras> buscarPorRango(@Param("inicio") LocalDateTime inicio, @Param("fin") LocalDateTime fin);
}