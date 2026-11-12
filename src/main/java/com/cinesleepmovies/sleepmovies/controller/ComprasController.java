package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Compras;
import com.cinesleepmovies.sleepmovies.repository.ComprasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
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

    @PutMapping("/{id}")
    public ResponseEntity<Compras> actualizarCompra(@PathVariable Integer id, @RequestBody Compras compras) {
        if (!comprasRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        compras.setIdcompras(id);
        return ResponseEntity.ok(comprasRepository.save(compras));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Integer id) {
        if (!comprasRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        comprasRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}