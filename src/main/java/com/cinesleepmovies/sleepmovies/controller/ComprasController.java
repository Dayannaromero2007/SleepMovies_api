package com.cinesleepmovies.sleepmovies.controller;

import com.cinesleepmovies.sleepmovies.model.Compras;
import com.cinesleepmovies.sleepmovies.service.ComprasService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "http://localhost:4200")
public class ComprasController {

    private final ComprasService comprasService;

    public ComprasController(ComprasService comprasService) {
        this.comprasService = comprasService;
    }

    @GetMapping
    public List<Compras> listar() {
        return comprasService.listar();
    }

    // Ejemplo: /api/compras/historial?desde=2026-11-01&hasta=2026-11-18
    // Para un solo día, usa la misma fecha en desde y hasta
    @GetMapping("/historial")
    public List<Compras> historial(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return comprasService.listarPorFechas(desde, hasta);
    }

    @PostMapping
    public ResponseEntity<Compras> crear(@RequestBody Compras compra) {
        return ResponseEntity.status(HttpStatus.CREATED).body(comprasService.crear(compra));
    }
}