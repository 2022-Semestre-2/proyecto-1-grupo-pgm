/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp;

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

