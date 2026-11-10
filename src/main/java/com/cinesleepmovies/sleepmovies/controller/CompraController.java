package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Compra;
import com.cinesleepmovies.sleepmovies.repository.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "http://localhost:4200")
public class CompraController {

    @Autowired
    private CompraRepository compraRepository;

    @GetMapping
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    @PostMapping
    public Compra guardarCompra(@RequestBody Compra compra) {
        return compraRepository.save(compra);
    }
}