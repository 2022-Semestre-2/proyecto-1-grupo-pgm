package taller;

import java.util.ArrayList;

import Enums.Provincia;
import Enums.TipoUsuario;

public class Usuario {
    //esta es la clase principal
    private  TipoUsuario tipo;
    private  String userName;
    private  String userPass;
    private  String name;
    private  String apellido;
    private String ID;
    private String tipoID;
    String telefono;
    String correo;
    String rol;
    String fechaDeingreso;
    Provincia provincia;
    String Canton;
    String fechaCumple;

    ArrayList<Vehiculo> vehiculos;


    Usuario(TipoUsuario tipo, String iD, String tipoID,int x) {
        this.tipo = tipo;
        ID = iD;
        this.tipoID = tipoID;
        if (this.tipo.toString().equals("CLIENTE")){
            this.vehiculos=new ArrayList<Vehiculo>();
        }
        else{

        }
        
    }


    Usuario(TipoUsuario tipo,String usr,String pass){
        this.tipo=tipo;
        this.userName=usr;
        this.userPass=pass;
        if (this.tipo.toString().equals("CLIENTE")){

        }
        else{

        }
        this.userName=usr;
        this.userPass=pass;
        this.vehiculos=new ArrayList<Vehiculo>();
    }



    public Usuario(String nombre, String tipoIdentificacion, String identificacion, String rol2, String fechaIngreso,int telefono2, String correo2, String usuario, String contrasena) {
        this.name=nombre;
        this.tipoID=tipoIdentificacion;
        this.ID=identificacion;
        this.rol=rol2;
        this.fechaDeingreso=fechaIngreso;
        this.telefono=String.valueOf(telefono2);
        this.correo=correo2;
        this.userName=usuario;
        this.userPass=contrasena;
    }


    public TipoUsuario getTipo() {
        return tipo;
    }


    public String getUserName() {
        return userName;
    }


    public String getUserPass() {
        return userPass;
    }


    public String getName() {
        return name;
    }


    public String getApellido() {
        return apellido;
    }


    public String getTelefono() {
        return telefono;
    }


    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }


    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return ID.hashCode();
    }


    public String getID() {
        return ID;
    }


    public String getTipoID() {
        return tipoID;
    }


    public String getCorreo() {
        return correo;
    }


    public String getRol() {
        return rol;
    }


    public String getFechaDeingreso() {
        return fechaDeingreso;
    }


    public Provincia getProvincia() {
        return provincia;
    }


    public String getCanton() {
        return Canton;
    }


    public String getFechaCumple() {
        return fechaCumple;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ID;
    }


    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public void setID(String iD) {
        ID = iD;
    }


    public void setTipoID(String tipoID) {
        this.tipoID = tipoID;
    }


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public void setRol(String rol) {
        this.rol = rol;
    }


    public void setFechaDeingreso(String fechaDeingreso) {
        this.fechaDeingreso = fechaDeingreso;
    }


    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }


    public void setCanton(String canton) {
        Canton = canton;
    }


    public void setFechaCumple(String fechaCumple) {
        this.fechaCumple = fechaCumple;
    }


    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void addVehiculo(Vehiculo tmp){
        vehiculos.add(tmp);
    }

}
