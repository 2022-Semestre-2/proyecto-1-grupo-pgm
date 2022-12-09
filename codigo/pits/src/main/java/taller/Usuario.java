package taller;

import java.util.ArrayList;

import Enums.ETipoUsuario;

public class Usuario {
    //esta es la clase principal
    public ETipoUsuario tipo;
    public String userName;
    public String userPass;
    ArrayList<Vehiculo> vehiculos;


    Usuario(ETipoUsuario test,String usr,String pass){   //constructor de prueba de prueba
        this.tipo=test;
        this.userName=usr;
        this.userPass=pass;
        this.vehiculos=new ArrayList<Vehiculo>();
        Vehiculo tmp = new Vehiculo("V1");
        vehiculos.add(tmp);
        tmp= new Vehiculo("A12");
        vehiculos.add(tmp);
    }

}
