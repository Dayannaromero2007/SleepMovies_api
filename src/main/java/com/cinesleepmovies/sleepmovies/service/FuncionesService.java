package com.cinesleepmovies.sleepmovies.service;

import com.cinesleepmovies.sleepmovies.model.Funciones;
import com.cinesleepmovies.sleepmovies.model.Peliculas;
import com.cinesleepmovies.sleepmovies.repository.FuncionesRepository;
import com.cinesleepmovies.sleepmovies.repository.PeliculasRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FuncionesService {

    private final FuncionesRepository repository;
    private final PeliculasRepository peliculasRepository;

    public FuncionesService(FuncionesRepository repository, PeliculasRepository peliculasRepository) {
        this.repository = repository;
        this.peliculasRepository = peliculasRepository;
    }

    public List<Funciones> listar() {
        return repository.findAll();
    }

    public Funciones buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Función no encontrada"));
    }

    public Funciones crear(Funciones funcion) {
        validarYAsignarPelicula(funcion);
        return repository.save(funcion);
    }

    public Funciones actualizar(Integer id, Funciones funcion) {
        buscarPorId(id); // lanza 404 si no existe
        funcion.setIdfunciones(id);
        validarYAsignarPelicula(funcion);
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

    // 400 si faltan datos, 404 si la película no existe
    private void validarYAsignarPelicula(Funciones funcion) {
        if (funcion.getSala() == null || funcion.getSala().isBlank()
                || funcion.getFechaHora() == null
                || funcion.getPrecio() == null || funcion.getPrecio() < 0
                || funcion.getCuposDisponibles() == null || funcion.getCuposDisponibles() < 0
                || funcion.getPelicula() == null || funcion.getPelicula().getIdpeliculas() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Sala, fecha/hora, precio, cupos y película son obligatorios");
        }
        Peliculas pelicula = peliculasRepository.findById(funcion.getPelicula().getIdpeliculas())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada"));
        funcion.setPelicula(pelicula);
    }
}