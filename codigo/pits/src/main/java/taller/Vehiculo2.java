/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package taller;

import com.google.gson.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Esta clase se encarga de modificar la base de datos en un archivo JSON para agregar y eliminar Marcas y Modelos
 * @author Mynell J. Myers
 * @version 1.7
 */

public class Vehiculo2 {
    //Variable que contiene la direccion del archivo JSON donde se guardan los datos
    private final String direccion= "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Vehiculo\\src\\main\\java\\com\\mycompany\\vehiculo\\data.json";
    public static void main(String[] args) throws FileNotFoundException{
    }
    /**
     * Metodo que se encarga de incluir una marca a la base de datos 
     * @param nombre Nombre de la marca a registrar 
     * @param categoria Categoria de la marca a registrar (4x4, Jeap, Sedan, PickUp, Suv...)
     * @throws FileNotFoundException Al no encontrar al archivo de la direccion designada
     */
    public void incluirMarca(String nombre, String categoria) throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonObject agregar= new JsonObject();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray arr= obj.get("Marcas").getAsJsonArray();
        agregar.addProperty("Nombre", nombre);
        agregar.addProperty("Categoria", categoria);
        arr.add(agregar);
        if (!new Vehiculo2().existeEnArchivo(nombre)){
            try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                nuevo.add("Marcas", arr);
                nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                nuevo.add("Servicios", obj.get("Servicios").getAsJsonArray());
                String jsonString= new Gson().toJson(nuevo);
                escritor.write(jsonString);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Marca ya existe en archivo","Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo que se encarga de eliminar una marca de la base de datos JSON
     * @param nombre Nombre de la marca a eliminar
     * @throws FileNotFoundException Al no encontrar el arhivo en la direccion designada
     */
    public void eliminarMarca(String nombre)throws FileNotFoundException{
        boolean condicion= true;
        JsonObject nuevo= new JsonObject();
        JsonArray arr = new JsonArray();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Marcas").getAsJsonArray();
        if (!new Vehiculo2().ligadoAModelo(nombre)){
            for (JsonElement marca : marcas){
                JsonObject indicador= marca.getAsJsonObject();
                if (!("\""+nombre+"\"").equals(indicador.get("Nombre").toString())){
                    arr.add(marca);
                }else{
                    condicion=false;
                }
            }
            try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                nuevo.add("Marcas", arr);
                nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                nuevo.add("Servicios", obj.get("Servicios").getAsJsonArray());
                String jsonString= new Gson().toJson(nuevo);
                escritor.write(jsonString);
            }catch(Exception e){
                e.printStackTrace();}
        }else{
            JOptionPane.showMessageDialog(null, "Marca ligada a Modelo/Imposible de eliminar","Error",JOptionPane.WARNING_MESSAGE);
            condicion=false;
        }
        if (condicion){
            JOptionPane.showMessageDialog(null,"Marca no encontrada","Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo para incluir un nuevo modelo a la base de datos
     * @param modelo nombre del nuevo modelo 
     * @param marca Marca de las registradas anteriormente para el modelo 
     * @param asientos Cantidad de asientos del modelo
     * @param puertas Cantidad de puertas del modelo
     * @param combustible Tipo de combustible que utiliza el modelo
     * @param transmision Tipo de transmision que utiliza el modelo
     * @throws FileNotFoundException Al no encontrar el archivo en la direccion designada
     */
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
        if (new Vehiculo2().existeEnArchivo(marca)){
            if (new Vehiculo2().ligadoAModelo(marca)){
                try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                    nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                    nuevo.add("Modelos", arr);
                    nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                    nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                    nuevo.add("Servicios", obj.get("Servicios").getAsJsonArray());
                    String jsonString= new Gson().toJson(nuevo);
                    escritor.write(jsonString);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Marca ya esta en uso","Error",JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Marca no existe","Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo que se encarga de eliminar un modelo de la base de datos JSON
     * @param nombre Nombre del modelo a eliminar
     * @throws FileNotFoundException Al no encontrar el archivo en la direccion designada 
     */
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
            nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
            nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
            nuevo.add("Servicios", obj.get("Servicios").getAsJsonArray());
            String jsonString= new Gson().toJson(nuevo);
            escritor.write(jsonString);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Metodo que confirma la existencia de una marca en la base de datos
     * @param nombre Nombre de la marca a buscar 
     * @return Si es verdadero es porque se encuentra en el archivo, de lo contrario no
     * @throws FileNotFoundException Al no encontrar el archivo de la direccion designada 
     */
    private boolean existeEnArchivo(String nombre) throws FileNotFoundException{
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Marcas").getAsJsonArray();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (("\""+nombre+"\"").equals(indicador.get("Nombre").toString())){
                return true;
            }
        }
        return false;
    }
    /**
     * Metodo para descubrir si alguna marca esta en uso pot algun modelo
     * @param nombre Nombre de la marca a indetificar 
     * @return True si se encuentra en uso, False si no se encuentra en uso
     * @throws FileNotFoundException Error al no encontrar el archivo
     */
    private boolean ligadoAModelo (String nombre) throws FileNotFoundException{
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Modelos").getAsJsonArray();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (("\""+nombre+"\"").equals(indicador.get("Marca").toString())){
                return true;
            }
        }
        return false;
    }
}
