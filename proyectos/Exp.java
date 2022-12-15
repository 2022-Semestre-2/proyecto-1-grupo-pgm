/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.exp;

import com.google.gson.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Exp {
    private final String direccion= "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Vehiculo\\src\\main\\java\\com\\mycompany\\exp\\newjson.json";
    public static void main(String[] args) throws FileNotFoundException{
        Exp t1 = new Exp();
        t1.eliminarModelo("6162");
    }
    public void incluirMarca(String nombre, String categoria) throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonObject agregar= new JsonObject();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray arr= obj.get("Marcas").getAsJsonArray();
        agregar.addProperty("Nombre", nombre);
        agregar.addProperty("Categoria", categoria);
        arr.add(agregar);
        try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
            nuevo.add("Marcas", arr);
            nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
            String jsonString= new Gson().toJson(nuevo);
            escritor.write(jsonString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void eliminarMarca(String nombre)throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonArray arr = new JsonArray();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Marcas").getAsJsonArray();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (!("\""+nombre+"\"").equals(indicador.get("Nombre").toString())){
                arr.add(marca);
            }
        }
        try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
            nuevo.add("Marcas", arr);
            nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
            String jsonString= new Gson().toJson(nuevo);
            escritor.write(jsonString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void incluirModelo(String modelo,String marca, int asientos, int puertas,String combustible,String transmision)throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonObject agregar= new JsonObject();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray arr= obj.get("Modelos").getAsJsonArray();
        agregar.addProperty("Nombre", modelo);
        agregar.addProperty("Marca", marca);
        agregar.addProperty("Asientos", asientos);
        agregar.addProperty("Puertas", puertas);
        agregar.addProperty("Combustible", combustible);
        agregar.addProperty("Transmision", transmision);
        arr.add(agregar);
        try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
            nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
            nuevo.add("Modelos", arr);
            String jsonString= new Gson().toJson(nuevo);
            escritor.write(jsonString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void eliminarModelo(String nombre)throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonArray arr = new JsonArray();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray modelos= obj.get("Modelos").getAsJsonArray();
        for (JsonElement modelo : modelos){
            JsonObject indicador= modelo.getAsJsonObject();
            if (!("\""+nombre+"\"").equals(indicador.get("Nombre").toString())){
                arr.add(modelo);
            }
        }
        try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
            nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
            nuevo.add("Modelos", arr);
            String jsonString= new Gson().toJson(nuevo);
            escritor.write(jsonString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void existeEnArchivo(){}
}
