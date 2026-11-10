package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Pelicula;
import com.cinesleepmovies.sleepmovies.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "http://localhost:4200")

public class PeliculaController {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @GetMapping
    public List<Pelicula> listarPeliculas() {
        return peliculaRepository.findAll();
    }

    @PostMapping
    public Pelicula guardarPelicula(@RequestBody Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }
}
