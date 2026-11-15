package com.cinesleepmovies.sleepmovies.service;

import com.cinesleepmovies.sleepmovies.model.Funciones;
import com.cinesleepmovies.sleepmovies.repository.FuncionesRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionesService {

    private final FuncionesRepository repository;

    public FuncionesService(FuncionesRepository repository) {
        this.repository = repository;
    }

    public List<Funciones> listar() {
        return repository.findAll();
    }

    public Funciones buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada"));
    }

    public Funciones crear(Funciones funcion) {
        if (funcion.getSala() == null || funcion.getSala().isBlank() || funcion.getFechaHora() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sala y fecha/hora son obligatorias");
        }
        return repository.save(funcion);
    }

    public Funciones actualizar(Integer id, Funciones funcion) {
        buscarPorId(id);
        funcion.setIdfunciones(id);
        return repository.save(funcion);
    }

    public void eliminar(Integer id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede eliminar: tiene compras asociadas");
        }
    }
}