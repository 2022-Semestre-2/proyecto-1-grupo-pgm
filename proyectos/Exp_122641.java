/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Array;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.*;
import javax.swing.*;

public class Exp {
    public static void main(String[] args) throws Exception{
        //Exp t1= new Exp();
        //t1.incluirMarca("Toyota", "4x4");
    }
    public void incluirMarca(String nombre,String categoria)throws Exception{
        JSONObject data= (JSONObject)new JSONParser().parse(new FileReader("newjson.json"));
        JSONArray arr= data.getJSONArray("Marcas");
        String direccion="C:/Users/Usuario/Documents/NetBeansProjects/exp/src/main/java/com/mycompany/exp/newjson.json"; //ruta del archivo JSON, usar "/"
        JSONObject info =new JSONObject();
        try{
            info.put("Marca", nombre); //Ingresar datos
            info.put("Categoria",categoria);
        }catch(JSONException e){
            e.printStackTrace();
        }
        try (PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
            Exp guia= new Exp();
            escritor.write("Marcas :"+(guia.concatArray(arr, (JSONArray) (Array) info)).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void eliminarMarca(String nombre) throws FileNotFoundException{
    }
    public void incluirModelo(String modelo,String marca, int asientos, int puertas,String combustible,String transmision){}
    public void eliminarModelo(long nombre){}
    public void leerArchivo(){   
    }
    private JSONArray concatArray(JSONArray arr1, JSONArray arr2) throws JSONException {
        JSONArray result = new JSONArray();
        for (int i = 0; i < arr1.length(); i++) {
            result.put(arr1.get(i));
        }
        for (int i = 0; i < arr2.length(); i++) {
            result.put(arr2.get(i));
        }
        return result;
    }
}


import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import org.json.JSONException;
import org.json.JSONObject;

public class Exp {
    public static void main(String[] args){
    }
    public void incluirMarca(String nombre,String categoria){
        String direccion= ""; // Aqui ingresar la ruta del archivo JSON
        JSONObject info =new JSONObject();
        try{
            info.put("Marca", nombre); //Ingresar datos
            info.put("Categoria",categoria);
        }catch(JSONException e){
            e.printStackTrace();
        }
        try (PrintWriter out= new PrintWriter(new FileWriter(direccion))){
           out.write(info.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void eliminarMarca(String nombre) throws FileNotFoundException{
    }
    public void incluirModelo(String modelo,String marca, int asientos, int puertas,String combustible,String transmision){}
    public void eliminarModelo(long nombre){}
}

