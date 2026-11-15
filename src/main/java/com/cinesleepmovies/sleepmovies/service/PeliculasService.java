package com.cinesleepmovies.sleepmovies.service;

import com.cinesleepmovies.sleepmovies.model.Peliculas;
import com.cinesleepmovies.sleepmovies.repository.PeliculasRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PeliculasService {

    private final PeliculasRepository repository;

    public PeliculasService(PeliculasRepository repository) {
        this.repository = repository;
    }

    public List<Peliculas> listar() {
        return repository.findAll();
    }

    public Peliculas buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Película no encontrada"));
    }

    public Peliculas crear(Peliculas pelicula) {
        if (pelicula.getTitulo() == null || pelicula.getTitulo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El título es obligatorio");
        }
        return repository.save(pelicula);
    }

    public Peliculas actualizar(Integer id, Peliculas pelicula) {
        buscarPorId(id); // lanza 404 si no existe
        pelicula.setIdpeliculas(id);
        return repository.save(pelicula);
    }

    public void eliminar(Integer id) {
        buscarPorId(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "No se puede eliminar: tiene funciones asociadas");
        }
    }
}
