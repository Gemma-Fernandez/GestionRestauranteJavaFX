package com.restaurante.app.model;
import java.io.Serializable;
import java.time.LocalDate;

public class Restaurante implements Serializable {
    private String nombre;
    private String ciudad;
    private int aforo;
    private boolean abierto;
    private LocalDate fechaApertura;

    //CONSTRUCTOR

    public Restaurante() {
    }
    public Restaurante(String nombre, String ciudad, int aforo, boolean abierto, LocalDate fechaApertura) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.aforo = aforo;
        this.abierto = abierto;
        this.fechaApertura = fechaApertura;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public int getAforo() { return aforo; }
    public void setAforo(int aforo) { this.aforo = aforo; }

    public boolean isAbierto() { return abierto; }
    public void setAbierto(boolean abierto) { this.abierto = abierto; }

    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate fechaApertura) { this.fechaApertura = fechaApertura; }
}
