package taller;

import java.util.ArrayList;

import Enums.ETipoUsuario;

public class Usuario {
    //esta es la clase principal
    public ETipoUsuario tipo;
    public String userName;
    public String userPass;

    ArrayList<Vehiculo> vehiculos;


    Usuario(ETipoUsuario tipo,String usr,String pass){   //constructor de prueba
        this.tipo=tipo;
        this.userName=usr;
        this.userPass=pass;
        this.vehiculos=new ArrayList<Vehiculo>();
    }

}
