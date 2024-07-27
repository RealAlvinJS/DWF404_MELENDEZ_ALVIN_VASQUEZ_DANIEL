package sv.edu.udb.www.dwfteoricoapi.models;

import java.math.BigDecimal;

public class Medicamento {
    private String id;
    private String nombre;
    private int cantidad;
    private BigDecimal precio;

    // Constructor por defecto
    public Medicamento() {
    }

    // Constructor
    public Medicamento(String id, String nombre, int cantidad, BigDecimal precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}