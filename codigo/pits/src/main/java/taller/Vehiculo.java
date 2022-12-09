package taller;

import java.io.Serializable;

import Enums.ECombustible;
import Enums.ETransmision;

public class Vehiculo {
    String modelo;
    String marca;
    int asientos;
    int puertas;
    int ahno;
    String placa;
    ECombustible combustible;
    ETransmision transmision;
    boolean mantenimiento;

    Vehiculo(String test){      //este es de prueba
        this.modelo=test;
        this.marca="TOYOTA";
        this.asientos = 4;
        this.puertas=4;
        this.ahno=1990;
        this.placa = "ABCDEFG";
        this.combustible=ECombustible.DIESEL;
        this.transmision=ETransmision.SECUENCIAL;
        this.mantenimiento=false;

    }
}
