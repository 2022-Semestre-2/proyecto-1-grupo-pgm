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

import Enums.ETipoUsuario;

public class BDUsuarios {
    ArrayList<Usuario> usuarios;

    BDUsuarios(){
        usuarios = new ArrayList<Usuario>();
        loadDB();
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
            Usuario tmp = new Usuario(ETipoUsuario.EMPLEADO, "admin", "admin");
            this.usuarios.add(tmp);
            saveJson();
        }
        System.out.println("a");
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

    
    public boolean userExists(String usrnm){
        for (Usuario usuario : usuarios) {
            if (usuario.userName==usrnm)
            return true;
        }
        return false;
    }
}
