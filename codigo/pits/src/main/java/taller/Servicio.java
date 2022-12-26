package taller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Enums.TipoServicio;

public class Servicio {
    private boolean activo;
    private Vehiculo vehiculo;           //placa debe estar en el vehiculo
    private TipoServicio tipoServicio;
    private String cedCliente;
    private String descripcionVehiculo;
    private String descripcionProblema;
    private ArrayList<String> partesAEnderezarPintar;
    private boolean poliza;
    private int folio; //numero de caso
    private int costo;
    private String fechaRecibido;
    private String fechaTEntrega;

    //para metodos is
    private boolean mecanica;
    private boolean enderezado;


    /** metodo para crear un servicio de cliente
     * MECANICA
     * @param vehiculo
     * @param cedCliente
     * @param descripcionVehiculo
     * @param descripcionProblema
     * @param costo
     * @param fechaRecibido
     * @param fechaTEntrega
     */
    public Servicio(Vehiculo vehiculo,String cedCliente,String descripcionVehiculo,String descripcionProblema,int costo,String fechaRecibido,String fechaTEntrega){
        this.vehiculo=vehiculo;
        this.cedCliente=cedCliente;
        this.descripcionVehiculo=descripcionVehiculo;
        this.descripcionProblema=descripcionProblema;
        this.costo=costo;
        this.fechaRecibido=fechaRecibido;
        this.fechaTEntrega=fechaTEntrega;
        this.mecanica=true;
        this.enderezado=false;
    }

    /** metodo para crear un servicio de cliente
     *  ENDEREZADO
     * @param vehiculo
     * @param cedCliente
     * @param descripcionVehiculo
     * @param partesAEnderezarPintar
     * @param poliza
     * @param folio
     * @param costo
     * @param fechaRecibido
     * @param fechaTEntrega
     */
    Servicio(Vehiculo vehiculo,String cedCliente,String descripcionVehiculo,ArrayList<String> partesAEnderezarPintar,boolean poliza, int folio,int costo, String fechaRecibido, String fechaTEntrega){
        this.vehiculo=vehiculo;
        this.cedCliente=cedCliente;
        this.descripcionVehiculo=descripcionVehiculo;
        this.partesAEnderezarPintar=partesAEnderezarPintar;
        this.poliza=poliza;
        this.folio=folio;
        this.costo=costo;
        this.fechaRecibido=fechaRecibido;
        this.fechaTEntrega=fechaTEntrega;
        this.mecanica=false;
        this.enderezado=true;
    }
    public boolean isActivo() {
        return activo;
    }
    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public TipoServicio getTipoServicio() {
        return tipoServicio;
    }
    public String getCedCliente() {
        return cedCliente;
    }
    public String getDescripcionVehiculo() {
        return descripcionVehiculo;
    }
    public String getDescripcionProblema() {
        return descripcionProblema;
    }
    public ArrayList<String> getPartesAEnderezarPintar() {
        return partesAEnderezarPintar;
    }
    public boolean isPoliza() {
        return poliza;
    }
    public int getFolio() {
        return folio;
    }
    public int getCosto() {
        return costo;
    }
    public String getFechaRecibido() {
        return fechaRecibido;
    }
    public String getFechaTEntrega() {
        return fechaTEntrega;
    }
    public boolean isMecanica() {
        return mecanica;
    }
    public boolean isEnderezado() {
        return enderezado;
    }
    
}
