package taller;

import java.io.Serializable;

import Enums.Combustible;
import Enums.Transmision;

public class Vehiculo {
    String modelo;
    String marca;
    int asientos;
    int puertas;
    int ahno;
    String placa;
    Combustible combustible;
    Transmision transmision;
    boolean mantenimiento;

    public Vehiculo() {
    }

    Vehiculo(String test){      //este es de prueba
        this.modelo=test;
        this.marca="TOYOTA";
        this.asientos = 4;
        this.puertas=4;
        this.ahno=1990;
        this.placa = test;
        this.combustible=Combustible.DIESEL;
        this.transmision=Transmision.SECUENCIAL;
        this.mantenimiento=false;

    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAsientos() {
        return asientos;
    }

    public int getPuertas() {
        return puertas;
    }

    public int getAhno() {
        return ahno;
    }

    public String getPlaca() {
        return placa;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public Transmision getTransmision() {
        return transmision;
    }

    public boolean isMantenimiento() {
        return mantenimiento;
    }

    @Override
    public String toString() {
        return placa;
    }
}
