package com.cinesleepmovies.sleepmovies.service;

import com.cinesleepmovies.sleepmovies.model.Compras;
import com.cinesleepmovies.sleepmovies.model.Funciones;
import com.cinesleepmovies.sleepmovies.repository.ComprasRepository;
import com.cinesleepmovies.sleepmovies.repository.FuncionesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
                || compra.getFunciones() == null || compra.getFunciones().isEmpty()
                || compra.getFunciones().get(0).getIdfunciones() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Nombre, cantidad de boletas y función son obligatorios");
        }

        // 404: la función debe existir
        Integer idFuncion = compra.getFunciones().get(0).getIdfunciones();
        Funciones funcion = funcionesRepository.findById(idFuncion)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada"));

        // Regla 1: cupos insuficientes -> 409 y no se modifica nada
        if (compra.getCantidadBoletas() > funcion.getCuposDisponibles()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cupos insuficientes");
        }

        // Regla 2: descontar cupos y registrar la compra (todo en una transacción)
        funcion.setCuposDisponibles(funcion.getCuposDisponibles() - compra.getCantidadBoletas());
        funcionesRepository.save(funcion);

        compra.setFunciones(new ArrayList<>(List.of(funcion)));
        return comprasRepository.save(compra);
    }
}
