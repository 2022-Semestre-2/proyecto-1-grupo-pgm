package taller;

import java.util.ArrayList;

import Enums.TipoUsuario;

public class Usuario {
    //esta es la clase principal
    private  TipoUsuario tipo;
    private  String userName;
    private  String userPass;
    private  String name;
    private  String apellido;
    
    
    String telefono;


    ArrayList<Vehiculo> vehiculos;


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

}
