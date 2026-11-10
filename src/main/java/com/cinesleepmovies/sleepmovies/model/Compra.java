package com.cinesleepmovies.sleepmovies.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompras")
    private Integer idcompras;

    @Column(name = "nombre_cliente", length = 100, nullable = false)
    private String nombreCliente;

    @Column(name = "cantidad_boletas", nullable = false)
    private Integer cantidadBoletas;

    @ManyToMany
    @JoinTable(
            name = "compras_has_funciones",
            joinColumns = @JoinColumn(name = "compras_idcompras"),
            inverseJoinColumns = @JoinColumn(name = "funciones_idfunciones")
    )
    private List<Funcion> funciones;

    public Compra() {}

    public Integer getIdcompras() { return idcompras; }
    public void setIdcompras(Integer idcompras) { this.idcompras = idcompras; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public Integer getCantidadBoletas() { return cantidadBoletas; }
    public void setCantidadBoletas(Integer cantidadBoletas) { this.cantidadBoletas = cantidadBoletas; }

    public List<Funcion> getFunciones() { return funciones; }
    public void setFunciones(List<Funcion> funciones) { this.funciones = funciones; }
}