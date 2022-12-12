package taller;

import java.util.Date;

import Enums.ETipoServicio;

public class Servicio {
    public Vehiculo vehiculo;           //placa debe estar en el vehiculo
    public ETipoServicio tipoServicio;
    String cedCliente;
    String descripcionVehiculo;
    String descripcionProblema;
    String[] partesAEnderezarPintar;
    boolean poliza;
    int folio; //numero de caso
    int costo;
    String fechaRecibido;
    String fechaTEntrega;

    /**
     * 
     */
    Servicio(Vehiculo vehiculo){
        this.vehiculo=vehiculo;
    }
    
}
