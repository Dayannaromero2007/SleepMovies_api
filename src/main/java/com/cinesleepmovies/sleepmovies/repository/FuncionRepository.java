package com.cinesleepmovies.sleepmovies.repository;

import com.cinesleepmovies.sleepmovies.model.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FuncionRepository extends JpaRepository<Funcion, Integer>{
}
