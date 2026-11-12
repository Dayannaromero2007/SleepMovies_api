package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Peliculas;
import com.cinesleepmovies.sleepmovies.repository.PeliculasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })

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

    @PutMapping("/{id}")
    public ResponseEntity<Peliculas> actualizarPelicula(@PathVariable Integer id, @RequestBody Peliculas peliculas) {
        if (!peliculasRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        peliculas.setIdpeliculas(id);
        return ResponseEntity.ok(peliculasRepository.save(peliculas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Integer id) {
        if (!peliculasRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        peliculasRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
