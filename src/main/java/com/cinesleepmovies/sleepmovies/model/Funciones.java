package com.cinesleepmovies.sleepmovies.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "funciones")
public class Funciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfunciones")
    private Integer idfunciones;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "sala", length = 45)
    private String sala;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "cupos_disponibles")
    private Integer cuposDisponibles;


    @ManyToMany
    @JoinTable(
            name = "peliculas_has_funciones",
            joinColumns = @JoinColumn(name = "funciones_idfunciones"),
            inverseJoinColumns = @JoinColumn(name = "peliculas_idpeliculas")
    )
    private List<Peliculas> peliculas;


    @ManyToMany(mappedBy = "funciones")
    private List<Compras> compras;

    public Funciones() {}

    public Integer getIdfunciones() { return idfunciones; }
    public void setIdfunciones(Integer idfunciones) { this.idfunciones = idfunciones; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getSala() { return sala; }
    public void setSala(String sala) { this.sala = sala; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public Integer getCuposDisponibles() { return cuposDisponibles; }
    public void setCuposDisponibles(Integer cuposDisponibles) { this.cuposDisponibles = cuposDisponibles; }

    public List<Peliculas> getPeliculas() { return peliculas; }
    public void setPeliculas(List<Peliculas> peliculas) { this.peliculas = peliculas; }

    public List<Compras> getCompras() { return compras; }
    public void setCompras(List<Compras> compras) { this.compras = compras; }
}