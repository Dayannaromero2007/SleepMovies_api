package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Compras;
import com.cinesleepmovies.sleepmovies.repository.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "http://localhost:4200")
public class ComprasController {

    @Autowired
    private ComprasRepository comprasRepository;

    @GetMapping
    public List<Compras> listarCompras() {
        return comprasRepository.findAll();
    }

    @PostMapping
    public Compras guardarCompra(@RequestBody Compras compras) {
        return comprasRepository.save(compras);
    }
}