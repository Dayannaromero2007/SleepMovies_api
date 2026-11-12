package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Peliculas;
import com.cinesleepmovies.sleepmovies.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "http://localhost:4200")

public class PeliculasController {
    @Autowired
    private PeliculasRepository peliculasRepository;

    @GetMapping
    public List<Peliculas> listarPeliculas() {
        return peliculasRepository.findAll();
    }

    @PostMapping
    public Peliculas guardarPelicula(@RequestBody Peliculas peliculas) {
        return peliculasRepository.save(peliculas);
    }
}
