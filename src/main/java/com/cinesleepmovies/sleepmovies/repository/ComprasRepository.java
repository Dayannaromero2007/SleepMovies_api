package com.cinesleepmovies.sleepmovies.repository;
import com.cinesleepmovies.sleepmovies.model.Compras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprasRepository extends JpaRepository<Compras, Integer> {
}
