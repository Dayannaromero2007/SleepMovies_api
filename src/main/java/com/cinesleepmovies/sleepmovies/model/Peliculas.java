package com.cinesleepmovies.sleepmovies.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "peliculas")

public class Peliculas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idpeliculas")
    private Integer idpeliculas;

    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Column(name = "genero", length = 45)
    private String genero;

    @Column(name = "duracion")
    private Integer duracion;

    @Column(name = "clasificacion", length = 10)
    private String clasificacion;

    @ManyToMany(mappedBy = "peliculas")
    private List<Funciones> funciones;

    public Peliculas() {}

    public Integer getIdpeliculas() { return idpeliculas; }
    public void setIdpeliculas(Integer idpeliculas) { this.idpeliculas = idpeliculas; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public Integer getDuracion() { return duracion; }
    public void setDuracion(Integer duracion) { this.duracion = duracion; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }

    public List<Funciones> getFunciones() { return funciones; }
    public void setFunciones(List<Funciones> funciones) { this.funciones = funciones; }
}
