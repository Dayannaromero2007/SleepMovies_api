package com.cinesleepmovies.sleepmovies.service;

import com.cinesleepmovies.sleepmovies.model.Compras;
import com.cinesleepmovies.sleepmovies.model.Funciones;
import com.cinesleepmovies.sleepmovies.repository.ComprasRepository;
import com.cinesleepmovies.sleepmovies.repository.FuncionesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComprasService {

    private final ComprasRepository comprasRepository;
    private final FuncionesRepository funcionesRepository;

    public ComprasService(ComprasRepository comprasRepository, FuncionesRepository funcionesRepository) {
        this.comprasRepository = comprasRepository;
        this.funcionesRepository = funcionesRepository;
    }

    public List<Compras> listar() {
        return comprasRepository.findAll();
    }

    @Transactional
    public Compras crear(Compras compra) {
        // 400: datos obligatorios
        if (compra.getNombreCliente() == null || compra.getNombreCliente().isBlank()
                || compra.getCantidadBoletas() == null || compra.getCantidadBoletas() < 1
                || compra.getFuncion() == null || compra.getFuncion().getIdfunciones() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nombre, cantidad de boletas y función son obligatorios");
        }

        // 404: la función debe existir
        Funciones funcion = funcionesRepository.findById(compra.getFuncion().getIdfunciones())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada"));

        int cupos = funcion.getCuposDisponibles() != null ? funcion.getCuposDisponibles() : 0;
        double precio = funcion.getPrecio() != null ? funcion.getPrecio() : 0.0;

        // Regla 1: cupos insuficientes -> 409 y no se modifica nada
        if (compra.getCantidadBoletas() > cupos) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cupos insuficientes");
        }

        // Regla 2: registrar la compra, calcular el total y descontar cupos (una sola transacción)
        funcion.setCuposDisponibles(cupos - compra.getCantidadBoletas());
        funcionesRepository.save(funcion);

        compra.setIdcompras(null); // siempre crea una compra nueva
        compra.setFuncion(funcion);
        compra.setValorTotal(precio * compra.getCantidadBoletas());
        compra.setFechaCompra(LocalDateTime.now());
        return comprasRepository.save(compra);
    }
}