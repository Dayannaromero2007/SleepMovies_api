package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Funciones;
import com.cinesleepmovies.sleepmovies.service.FuncionesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@CrossOrigin(origins = "http://localhost:4200",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class FuncionesController {

    private final FuncionesService service;

    public FuncionesController(FuncionesService service) {
        this.service = service;
    }

    @GetMapping
    public List<Funciones> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Funciones buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ResponseEntity<Funciones> crear(@RequestBody Funciones funcion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(funcion));
    }

    @PutMapping("/{id}")
    public Funciones actualizar(@PathVariable Integer id, @RequestBody Funciones funcion) {
        return service.actualizar(id, funcion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}