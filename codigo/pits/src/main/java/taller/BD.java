package taller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Enums.TipoUsuario;
/**
 * Base de datos 
 * Diagrama de clases https://drive.google.com/file/d/1BtV5ftL1CqnjO2gyDr8QG4pgQPPn4vX8/view?usp=sharing
 * @author ttc46
 */
public class BD {
    ArrayList<Usuario> usuarios;
    ArrayList<Servicio> servicios;

    BD(){
        usuarios = new ArrayList<Usuario>();
        servicios = new ArrayList<Servicio>();
        loadDB();
    }

    /**
     * Guarda la base de datos en "usuarios.json"
     */
    public void saveJson(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(usuarios);
        try (FileWriter arch = new FileWriter("usuarios.json")) {
            gson.toJson(usuarios, arch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Agrega un usuario a la lista de usuarios
     * @param usr Usuario a agregar
     */
    public void addUsr(Usuario usr){
        this.usuarios.add(usr);
    }



    /** agrega un servicio
     * @param servicio
     */
    public void addService(Servicio servicio){
        this.servicios.add(servicio);
    }


    /**  
     * @param nombreUsr nombre de usuario a buscar
     * @return null si no existe, el usuario si sí.
     */
    public Usuario getuUsuario(String nombreUsr){
        if (userExists(nombreUsr)){
            for (Usuario usuario : usuarios) {
                if(usuario.getUserName()==nombreUsr){
                    return usuario;
                }
            }
        }
        return null;
    }

    
    /**
     * Carga la base de datos
     */
    private void loadDB(){
        Gson gson = new Gson();
        Usuario[] myTypes;
        try {
            myTypes = gson.fromJson(new FileReader("usuarios.json"), Usuario[].class);      //ni puta idea de porque no me deja con arraylist pura
            for (int i = 0; i < myTypes.length; i++) {      //poner los elems de array en arraylist
                this.usuarios.add(myTypes[i]);
            }
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            Usuario tmp = new Usuario(TipoUsuario.EMPLEADO, "admin", "admin");
            this.usuarios.add(tmp);
            saveJson();
        }
        System.out.println("a");
    }

    /** Revisa si un usuario existe
     * @param usrnm
     * @return true si existe flase si no
     */
    private boolean userExists(String usrnm){
        for (Usuario usuario : usuarios) {
            if (usuario.getUserName()==usrnm)
            return true;
        }
        return false;
    }

}
