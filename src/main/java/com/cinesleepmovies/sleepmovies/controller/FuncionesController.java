package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Funciones;
import com.cinesleepmovies.sleepmovies.repository.FuncionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/funciones")
@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
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

    @PutMapping("/{id}")
    public ResponseEntity<Funciones> actualizarFuncion(@PathVariable Integer id, @RequestBody Funciones funciones) {
        if (!funcionesRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        funciones.setIdfunciones(id);
        return ResponseEntity.ok(funcionesRepository.save(funciones));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFuncion(@PathVariable Integer id) {
        if (!funcionesRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        funcionesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
