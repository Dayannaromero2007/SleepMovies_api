package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Compras;
import com.cinesleepmovies.sleepmovies.service.ComprasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "http://localhost:4200")
public class ComprasController {

    private final ComprasService service;

    public ComprasController(ComprasService service) {
        this.service = service;
    }

    @GetMapping
    public List<Compras> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Compras> crear(@RequestBody Compras compra) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(compra));
    }
}