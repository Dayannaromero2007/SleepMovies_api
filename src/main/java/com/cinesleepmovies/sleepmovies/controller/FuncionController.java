package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Funcion;
import com.cinesleepmovies.sleepmovies.repository.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@CrossOrigin(origins = "http://localhost:4200")
public class FuncionController {

    @Autowired
    private FuncionRepository funcionRepository;

    @GetMapping
    public List<Funcion> listarFunciones() {
        return funcionRepository.findAll();
    }

    @PostMapping
    public Funcion guardarFuncion(@RequestBody Funcion funcion) {
        return funcionRepository.save(funcion);
    }
}
