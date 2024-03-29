/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * Esta clase se encarga de modificar el contenido del archivo JSON del proyecto, enfocandose en agregar y eliminar clientes de la base de datos
 * @author Mynell J. Myers
 * @version 1.0
 */
public class Cliente {
    private final String direccion= "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Vehiculo\\src\\main\\java\\com\\mycompany\\vehiculo\\data.json";
    /**
     * Metodo para agregar un cliente a la lista de clientes de archivo JSON
     * @param nombre Nombre del Cliente 
     * @param apellidos Apellidos del Cliente 
     * @param identificacion Identificacion de cuidadado 
     * @param tipoIdentificacion Si es una identificacion extranjera o local
     * @param provincia Ingresar una de las provincias actuales que conforma Costa Rica donde habita el cliente
     * @param canton Canton de la provincia donde vive
     * @param fecha Fecha donde cumple años el Cliente
     * @param telefono Telefono vigente actual del Cliente
     * @param correo Correo Electronico vigente del Cliente
     * @throws java.io.FileNotFoundException Al archivo no estar encontrado en la direccion designada
     */
    public void incluirCliente(String nombre, String apellidos, String identificacion, String tipoIdentificacion, String provincia, String canton, String fecha, String telefono, String correo) throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonObject agregar= new JsonObject();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray arr= obj.get("Clientes").getAsJsonArray();
        agregar.addProperty("Nombre", nombre);
        agregar.addProperty("Apellidos", apellidos);
        agregar.addProperty("Identificacion", identificacion);
        agregar.addProperty("Tipo", tipoIdentificacion);
        agregar.addProperty("Provincia", provincia);
        agregar.addProperty("Canton", canton);
        agregar.addProperty("Fecha", fecha);
        agregar.addProperty("Telefono", telefono);
        agregar.addProperty("Correo", correo);
        agregar.add("Vehiculos", new JsonArray());
        arr.add(agregar);
        if ((((nombre.replaceAll(" ", "")).length()) != 0)&&(((apellidos.replaceAll(" ", "")).length()) != 0)&&(((identificacion.replaceAll(" ", "")).length()) != 0)&&(((tipoIdentificacion.replaceAll(" ", "")).length()) != 0)&&(((provincia.replaceAll(" ", "")).length()) != 0)&&(((canton.replaceAll(" ", "")).length()) != 0)&&(((fecha.replaceAll(" ", "")).length()) != 0)&&(((telefono.replaceAll(" ", "")).length()) != 0)&&(((correo.replaceAll(" ", "")).length()) != 0)){
            try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                nuevo.add("Clientes", arr);
                nuevo.add("Servicios", obj.get("Servicios").getAsJsonArray());
                String jsonString= new Gson().toJson(nuevo);
                escritor.write(jsonString);
                JOptionPane.showInternalMessageDialog(null, "Cliente Incluido", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Alguno de los valores son negativos", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo que elimina el cliente del archivo JSON de base de datos 
     * @param identificacion Identificacion del Cliente a eliminar
     * @throws FileNotFoundException Al archivo no estar encontrado en la direccion designada
     */
    public void eliminarCliente(String identificacion) throws FileNotFoundException{
        boolean condicion = true;
        JsonObject nuevo= new JsonObject();
        JsonArray arr = new JsonArray();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Clientes").getAsJsonArray();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (!("\""+identificacion+"\"").equals(indicador.get("Identificacion").toString())){
                arr.add(marca);
            }else{
                condicion = false;
            }
        }
        try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
            nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
            nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
            nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
            nuevo.add("Clientes", arr);
            nuevo.add("Servicios", obj.get("Servicios").getAsJsonArray());
            String jsonString= new Gson().toJson(nuevo);
            escritor.write(jsonString);
            JOptionPane.showInternalMessageDialog(null, "Cliente Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
        if (condicion){
            JOptionPane.showMessageDialog(null,"Cliente no encontrado","Error",JOptionPane.WARNING_MESSAGE);
        }
    }
}
