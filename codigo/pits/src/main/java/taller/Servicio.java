package taller;

import java.util.Date;

import Enums.TipoServicio;

public class Servicio {
    private boolean activo;
    private Vehiculo vehiculo;           //placa debe estar en el vehiculo
    private TipoServicio tipoServicio;
    private String cedCliente;
    private String descripcionVehiculo;
    private String descripcionProblema;
    private String[] partesAEnderezarPintar;
    private boolean poliza;
    private int folio; //numero de caso
    private int costo;
    private String fechaRecibido;
    private String fechaTEntrega;

    /**
     * 
     */
    Servicio(Vehiculo vehiculo){
        this.vehiculo=vehiculo;
    }
    
}
