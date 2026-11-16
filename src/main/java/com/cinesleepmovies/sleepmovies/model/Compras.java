package com.cinesleepmovies.sleepmovies.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
public class Compras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcompras")
    private Integer idcompras;

    @Column(name = "nombre_cliente", length = 100, nullable = false)
    private String nombreCliente;

    @Column(name = "cantidad_boletas", nullable = false)
    private Integer cantidadBoletas;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;

    @Column(name = "fecha_compra", nullable = false)
    private LocalDateTime fechaCompra;

    // Muchas compras -> 1 función
    @ManyToOne
    @JoinColumn(name = "id_funcion", nullable = false)
    private Funciones funcion;

    public Compras() {}

    public Integer getIdcompras() { return idcompras; }
    public void setIdcompras(Integer idcompras) { this.idcompras = idcompras; }

    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }

    public Integer getCantidadBoletas() { return cantidadBoletas; }
    public void setCantidadBoletas(Integer cantidadBoletas) { this.cantidadBoletas = cantidadBoletas; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public LocalDateTime getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDateTime fechaCompra) { this.fechaCompra = fechaCompra; }

    public Funciones getFuncion() { return funcion; }
    public void setFuncion(Funciones funcion) { this.funcion = funcion; }
}