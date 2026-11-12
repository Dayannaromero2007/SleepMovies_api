package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Funciones;
import com.cinesleepmovies.sleepmovies.repository.FuncionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionesController {

    @Autowired
    private FuncionesRepository funcionesRepository;

    @GetMapping
    public List<Funciones> listarFunciones() {
        return funcionesRepository.findAll();
    }

    @PostMapping
    public Funciones guardarFuncion(@RequestBody Funciones funciones) {
        return funcionesRepository.save(funciones);
    }
}
