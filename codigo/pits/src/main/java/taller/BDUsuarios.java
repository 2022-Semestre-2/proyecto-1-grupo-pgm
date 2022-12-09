package taller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.google.gson.*;

public class BDUsuarios {
    ArrayList<Usuario> usuarios;

    BDUsuarios(){
        usuarios = new ArrayList<Usuario>();

    }

    private void loadDB(){
        //aqui cargar todo lo del json a el arraylist
        //new Gson();
    }
    public void saveJson(){
        JsonObject jsonObj = new JsonObject();
        JsonArray jsonArray2 = new Gson().toJsonTree(usuarios).getAsJsonArray();
        jsonObj.add("BD", jsonArray2);
        //System.out.println(jsonObj.toString());
        try {  

            File file=new File("BDUsuarios.json");  
            file.createNewFile();  
            FileWriter fileWriter = new FileWriter(file);  
            fileWriter.write(jsonObj.toString());  
            fileWriter.flush();  
            fileWriter.close();  

        } catch (IOException e) {} 
    }
    public void addUsr(Usuario usr){
        this.usuarios.add(usr);
    }
}
