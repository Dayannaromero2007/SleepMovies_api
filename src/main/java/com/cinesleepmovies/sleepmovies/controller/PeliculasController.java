package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Peliculas;
import com.cinesleepmovies.sleepmovies.service.PeliculasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/peliculas")
@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PeliculasController {

    private final PeliculasService service;

    public PeliculasController(PeliculasService service) {
        this.service = service;
    }

    @GetMapping
    public List<Peliculas> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Peliculas buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Peliculas> crear(@RequestBody Peliculas pelicula) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(pelicula));
    }

    @PutMapping("/{id}")
    public Peliculas actualizar(@PathVariable Integer id, @RequestBody Peliculas pelicula) {
        return service.actualizar(id, pelicula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}