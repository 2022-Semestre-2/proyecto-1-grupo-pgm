package taller;

import java.util.ArrayList;

import Enums.ETipoUsuario;

public class Usuario {
    //esta es la clase principal
    public ETipoUsuario tipo;
    public String userName;
    public String userPass;
    public String name;
    public String apellido;
    
    String telefono;


    ArrayList<Vehiculo> vehiculos;


    Usuario(ETipoUsuario tipo,String usr,String pass){
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

}
