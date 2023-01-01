package taller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import com.google.gson.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import Enums.TipoUsuario;
/**
 * Base de datos 
 * Diagrama de clases https://drive.google.com/file/d/1BtV5ftL1CqnjO2gyDr8QG4pgQPPn4vX8/view?usp=sharing
 * @author ttc46 & Mynell
 */
public class BD {
    ArrayList<Usuario> usuarios;
    ArrayList<Servicio> servicios;
    private final String direccion= "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Taller\\src\\main\\java\\data.json";

    public BD(){
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
        Gson son = new GsonBuilder().setPrettyPrinting().create();
        json = son.toJson(servicios);
        try (FileWriter arch = new FileWriter("servicios.json")) {
            son.toJson(servicios, arch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Agrega un usuario a la lista de usuarios
     * @param usr Usuario a agregar
     */
    public void addUsr(Usuario usr){
        this.usuarios.add(usr);
        saveJson();
    }



    /** agrega un servicio
     * @param servicio
     */
    public void addService(Servicio servicio){
        this.servicios.add(servicio);
        saveJson();
    }


    /**  
     * @param nombreUsr nombre de usuario a buscar
     * @return null si no existe, el usuario si sí.
     */
    public Usuario getUsuario(String nombreUsr){
        if (userExists(nombreUsr)){
            for (Usuario usuario : usuarios) {
                if(usuario.getUserName()==nombreUsr||usuario.getID().equals(nombreUsr)){
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
        Usuario[] usrTMP;
        try {
            usrTMP = gson.fromJson(new FileReader("usuarios.json"), Usuario[].class);      //ni puta idea de porque no me deja con arraylist pura
            for (int i = 0; i < usrTMP.length; i++) {      //poner los elems de array en arraylist
                this.usuarios.add(usrTMP[i]);
            }
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            Usuario tmp = new Usuario(TipoUsuario.EMPLEADO, "admin", "admin");
            tmp.setID("999999999");
            this.usuarios.add(tmp);
            saveJson();
        }
        Servicio[] servsTMP;
        try {
            servsTMP = gson.fromJson(new FileReader("servicios.json"), Servicio[].class);      //ni puta idea de porque no me deja con arraylist pura
            for (int i = 0; i < servsTMP.length; i++) {      //poner los elems de array en arraylist
                this.servicios.add(servsTMP[i]);
            }
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
        }
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
        if (!existeEnArchivo(nombre,"Marcas")){
            if ((((nombre.replaceAll(" ", "")).length()) != 0)&&(((categoria.replaceAll(" ", "")).length()) != 0)){
                try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                    nuevo.add("Marcas", arr);
                    nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                    nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                    nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                    nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                    nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
                    String jsonString= new Gson().toJson(nuevo);
                    escritor.write(jsonString);
                    JOptionPane.showInternalMessageDialog(null, "Marca Agregada", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Asegurese que todos los campos tengan datos", "Error", JOptionPane.WARNING_MESSAGE);
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
        JsonObject nuevo= new JsonObject();
        JsonArray arr = new JsonArray();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Marcas").getAsJsonArray();
        if (!ligadoA(nombre,"Modelos","Marca")){
            if((((nombre.replaceAll(" ", "")).length()) != 0)){
                if (existeEnArchivo(nombre,"Marcas")){
                    for (JsonElement marca : marcas){
                        JsonObject indicador= marca.getAsJsonObject();
                        if (!("\""+nombre+"\"").equals(indicador.get("Nombre").toString())){
                            arr.add(marca);
                        }
                    }
                    try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                        nuevo.add("Marcas", arr);
                        nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                        nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                        nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                        nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                        nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
                        String jsonString= new Gson().toJson(nuevo);
                        escritor.write(jsonString);
                        JOptionPane.showInternalMessageDialog(null, "Marca Eliminada", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"Marca no encontrada","Error",JOptionPane.WARNING_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Ingrese un valor", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Marca ligada a Modelo/Imposible de eliminar","Error",JOptionPane.WARNING_MESSAGE);
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
    public void incluirModelo(String modelo,String marca, Integer asientos, Integer puertas,String combustible,String transmision)throws FileNotFoundException{
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
        if (existeEnArchivo(marca,"Marcas")){
            if ((((modelo.replaceAll(" ", "")).length()) != 0)&&(((marca.replaceAll(" ", "")).length()) != 0)&&(((combustible.replaceAll(" ", "")).length()) != 0)&&(((transmision.replaceAll(" ", "")).length()) != 0)){
                try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                    nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                    nuevo.add("Modelos", arr);
                    nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                    nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                    nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                    nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
                    String jsonString= new Gson().toJson(nuevo);
                    escritor.write(jsonString);
                    JOptionPane.showInternalMessageDialog(null, "Modelo Agregado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Alguno de los margenes es vacio", "Error", JOptionPane.WARNING_MESSAGE);
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
        if ((((nombre.replaceAll(" ", "")).length()) != 0)){
            if(new BD().existeEnArchivo(nombre, "Modelos")){
                if (!ligadoA(nombre,"Vehiculos","Modelo")){
                    try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                        nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                        nuevo.add("Modelos", arr);
                        nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                        nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                        nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                        nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
                        String jsonString= new Gson().toJson(nuevo);
                        escritor.write(jsonString);
                        JOptionPane.showInternalMessageDialog(null, "Modelo Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "El modelo esta ligado a un vehiculo","Error",0);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Modelo no encontrado", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese un valor", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo que confirma la existencia de una marca en la base de datos
     * @param nombre Nombre de la marca a buscar 
     * @return Si es verdadero es porque se encuentra en el archivo, de lo contrario no
     * @throws FileNotFoundException Al no encontrar el archivo de la direccion designada 
     */
    private boolean existeEnArchivo(String nombre, String tipo) throws FileNotFoundException{
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get(tipo).getAsJsonArray();
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
    public boolean ligadoA (String nombre, String tipo, String elemento) throws FileNotFoundException{
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get(tipo).getAsJsonArray();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (("\""+nombre+"\"").equals(indicador.get(elemento).toString())){
                return true;
            }
        }
        return false;
    }
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
            if (!existeCliente(identificacion)){
            try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                nuevo.add("Clientes", arr);
                nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
                String jsonString= new Gson().toJson(nuevo);
                escritor.write(jsonString);
                JOptionPane.showInternalMessageDialog(null, "Cliente Incluido", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                e.printStackTrace();
            }
            }else{
                JOptionPane.showMessageDialog(null,"Ya esta en uso esa identificacion","Error",0);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Alguno de los valores es vacio", "Error", JOptionPane.WARNING_MESSAGE);
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
        if (!ligadoA(identificacion,"Vehiculos","Identificacion")){
            if (identificacion.replaceAll(" ", "").length()>0){
            try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                nuevo.add("Clientes", arr);
                nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
                String jsonString= new Gson().toJson(nuevo);
                escritor.write(jsonString);
                JOptionPane.showInternalMessageDialog(null, "Cliente Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                e.printStackTrace();
            }
            }else{
                condicion=false;
                JOptionPane.showMessageDialog(null, "Ingrese valores en los espacios", "Error", 0);
            }
        }else{
            JOptionPane.showMessageDialog(null, "El cliente tiene vehiculos registrados", "Error", 0);
        }
        if (condicion){
            JOptionPane.showMessageDialog(null,"Cliente no encontrado","Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo para incluir un empleado en la base de datos json
     * @param usuario nombre clave del usuario elegido por el registrador 
     * @param contrasenha contrasenha ligada al usuario
     * @param nombre nombre del empleado
     * @param apellidos 
     * @param identificacion 
     * @param tipoIdentificacion puede ser identificacion nacional o extranjera
     * @param rol si es Pintor o se encarga de enderezado
     * @param fecha Fecha en la que se unio a la empresa
     * @param telefono
     * @param correo
     * @throws FileNotFoundException 
     */
    public void incluirEmpleado(String usuario,String contrasenha,String nombre, String apellidos, String identificacion, String tipoIdentificacion, String rol, String fecha, String telefono, String correo) throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonObject agregar= new JsonObject();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray arr= obj.get("Empleados").getAsJsonArray();
        agregar.addProperty("Nombre", nombre);
        agregar.addProperty("Apellidos", apellidos);
        agregar.addProperty("Identificacion", identificacion);
        agregar.addProperty("Tipo", tipoIdentificacion);
        agregar.addProperty("Rol", rol);
        agregar.addProperty("Fecha", fecha);
        agregar.addProperty("Telefono", telefono);
        agregar.addProperty("Correo", correo);
        agregar.addProperty("Usuario",usuario);
        agregar.addProperty("Contrasenha", contrasenha);
        arr.add(agregar);
        if ((((nombre.replaceAll(" ", "")).length()) != 0)&&(((apellidos.replaceAll(" ", "")).length()) != 0)&&(((identificacion.replaceAll(" ", "")).length()) != 0)&&(((tipoIdentificacion.replaceAll(" ", "")).length()) != 0)&&(((rol.replaceAll(" ", "")).length()) != 0)&&(((fecha.replaceAll(" ", "")).length()) != 0)&&(((telefono.replaceAll(" ", "")).length()) != 0)&&(((correo.replaceAll(" ", "")).length()) != 0)){
            if (!ligadoA(usuario,"Empleados","Usuario")){
                if (!ligadoA(usuario,"Usuarios","Usuario")){
                    try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                        nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                        nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                        nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                        nuevo.add("Empleados", arr);
                        nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                        nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
                        String jsonString= new Gson().toJson(nuevo);
                        escritor.write(jsonString);
                        JOptionPane.showInternalMessageDialog(null, "Empleado Incluido", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Usuario ya existe", "Error", 0);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Usuario ya existe", "Error", 0);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Alguno de los valores son es vacio", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo para eliminar un empleado de la base de datos json
     * @param identificacion Identificacion del empleado a eliminar
     * @throws FileNotFoundException 
     */
    public void eliminarEmpleado(String identificacion) throws FileNotFoundException{
        boolean condicion = true;
        JsonObject nuevo= new JsonObject();
        JsonArray arr = new JsonArray();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Empleados").getAsJsonArray();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (!("\""+identificacion+"\"").equals(indicador.get("Identificacion").toString())){
                arr.add(marca);
            }else{
                condicion = false;
            }
        }
        if (identificacion.replaceAll(" ", "").length()>0){
        try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
            nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
            nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
            nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
            nuevo.add("Empleados", arr);
            nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
            nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
            String jsonString= new Gson().toJson(nuevo);
            escritor.write(jsonString);
            JOptionPane.showInternalMessageDialog(null, "Empleado Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
            e.printStackTrace();
        }
        }else{
            condicion=false;
            JOptionPane.showMessageDialog(null, "Algun parametro esta vacio", "Error", 0);
        }
        if (condicion){
            JOptionPane.showMessageDialog(null,"Empleado no encontrado","Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo para incluir un usuario a la base de datos json
     * @param usuario nombre de usuario
     * @param contrasenha
     * @throws FileNotFoundException 
     */
    public void incluirUsuario(String usuario, String contrasenha) throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonObject agregar= new JsonObject();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray arr= obj.get("Usuarios").getAsJsonArray();
        agregar.addProperty("Usuario", usuario);
        agregar.addProperty("Contrasenha", contrasenha);
        arr.add(agregar);
        if ((((usuario.replaceAll(" ", "")).length()) != 0)&&(((contrasenha.replaceAll(" ", "")).length()) != 0)){
            try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                nuevo.add("Usuarios", arr);
                nuevo.add("Vehiculos", obj.get("Vehiculos").getAsJsonArray());
                String jsonString= new Gson().toJson(nuevo);
                escritor.write(jsonString);
                JOptionPane.showInternalMessageDialog(null, "Usuario Agregado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Asegurese que todos los campos tengan datos", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo para incluir un vehiculo a un cliente registrado 
     * @param identificacion Identificacion del cliente a enlazar
     * @param marca Marca del vehiculo
     * @param modelo Modelo que sea ligado a la marca del vehiculo
     * @param anho Abho de fabricacion del vehiculo
     * @param placa Placa de identificacion del vehiculo
     * @throws FileNotFoundException 
     */
    public void setVehiculoUsuario(String identificacion, String marca, String modelo, String anho, String placa) throws FileNotFoundException{
        JsonObject nuevo= new JsonObject();
        JsonObject agregar= new JsonObject();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray arr= obj.get("Vehiculos").getAsJsonArray();
        agregar.addProperty("Identificacion", identificacion);
        agregar.addProperty("Marca", marca);
        agregar.addProperty("Modelo", modelo);
        agregar.addProperty("Anho", anho);
        agregar.addProperty("Placa", placa);
        arr.add(agregar);
        if ((((identificacion.replaceAll(" ", "")).length()) != 0)&&(((marca.replaceAll(" ", "")).length()) != 0)&&(((modelo.replaceAll(" ", "")).length()) != 0)&&(((anho.replaceAll(" ", "")).length()) != 0)&&(((placa.replaceAll(" ", "")).length()) != 0)){
            try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                nuevo.add("Empleados", obj.get("Empleados").getAsJsonArray());
                nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                nuevo.add("Vehiculos",arr);
                String jsonString= new Gson().toJson(nuevo);
                escritor.write(jsonString);
                JOptionPane.showInternalMessageDialog(null, "Vehiculo Agregado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Asegurese que todos los campos tengan datos", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo encargado de obtener todos los vehiculos ligados a un cliente en un JsonArray
     * @param identificacion ID del cliente a consultar vehiculos
     * @return una lista con los vehiculos del cliente/ Estara vacio si no presenta vehiculos
     * @throws FileNotFoundException 
     */
    public JsonArray getVehiculosUsuario(String identificacion) throws FileNotFoundException{
        JsonArray nuevo= new JsonArray();
        JsonArray vehiculos= (new JsonParser().parse(new FileReader(direccion)).getAsJsonObject()).get("Vehiculos").getAsJsonArray();
        if(identificacion.replaceAll(" ", "").length() != 0){
            if (existeCliente(identificacion)){
                for(JsonElement vehiculo:vehiculos){
                    if (vehiculo.getAsJsonObject().get("Identificacion").getAsString().equals("\""+identificacion+"\"")){
                        nuevo.add(vehiculo);
                    }     
                }
                return nuevo;
            }else{
                JOptionPane.showMessageDialog(null, "No existe esa identificacion","Error",0);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Alguno de los espacios esta vacio", "Error", 0);
        }
        return null;
    }
    /**
     * Metodo para descubri si un cliente existe en la base de datos json
     * @param identificacion ID del cliente a buscar 
     * @return true si existe/ false de lo contrario
     * @throws FileNotFoundException 
     */
    private boolean existeCliente(String identificacion) throws FileNotFoundException{
        JsonArray clientes=(new JsonParser().parse(new FileReader(direccion)).getAsJsonObject()).get("Clientes").getAsJsonArray();
        if(identificacion.replaceAll(" ", "").length() != 0){
            for (JsonElement cliente: clientes){
                if (cliente.getAsJsonObject().get("Identificacion").getAsString().equals("\""+identificacion+"\"")){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Metodo para remover un vehiculo de un cliente 
     * @param identificacion ID del cliente al que pertence el vehiculo
     * @param placa Placa del vehiculo a eliminar 
     * @throws FileNotFoundException 
     */
    public void removeVehiculo(String identificacion,String placa) throws FileNotFoundException{
        boolean condicion = true;
        JsonObject nuevo= new JsonObject();
        JsonArray arr = new JsonArray();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Vehiculos").getAsJsonArray();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (("\""+identificacion+"\"").equals(indicador.get("Identificacion").toString())&&("\""+placa+"\"").equals(indicador.get("Placa").toString())){
                condicion=false;
            }else{
                arr.add(marca);
            }
        }
        if (!vehiculoInServicio(placa)){
            try(PrintWriter escritor= new PrintWriter(new FileWriter(direccion))){
                nuevo.add("Marcas", obj.get("Marcas").getAsJsonArray());
                nuevo.add("Modelos", obj.get("Modelos").getAsJsonArray());
                nuevo.add("Clientes", obj.get("Clientes").getAsJsonArray());
                nuevo.add("Empleados", obj.get("Clientes").getAsJsonArray());
                nuevo.add("Usuarios", obj.get("Usuarios").getAsJsonArray());
                nuevo.add("Vehiculos", arr);
                String jsonString= new Gson().toJson(nuevo);
                escritor.write(jsonString);
                JOptionPane.showInternalMessageDialog(null, "Vehiculo Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Vehiculo ligado a Servicio", "Error", 0);
        }
        if (condicion){
            JOptionPane.showMessageDialog(null,"Vehiculo no encontrado","Error",JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Metodo para saber si un vehiculo se encuentra enlazado a un servicio
     * @param nombre Placa del vehiculo a consultar
     * @return true si se encuentra en servicio / false de lo contrario
     */
    public boolean vehiculoInServicio(String nombre){
        for (Servicio servicio:servicios){
            if (servicio.getPlaca()!= null && servicio.getPlaca().equals(nombre)){
                return true;
            }
        }
        return false;
    }
    public Vehiculo getVehiculoByuser(String cedula,String placa){
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getTipo().equals(TipoUsuario.CLIENTE)&&usuarios.get(i).getID().equals(cedula)){
                ArrayList<Vehiculo> tmp = usuarios.get(i).getVehiculos();
                for (int j = 0; j < tmp.size(); j++) {
                    if(tmp.get(j).getPlaca().equals(placa)){
                        return tmp.get(j);
                    }
                }
            }
        }
        return null;
    }
    /**
     * Metodo para obtener usuarios 
     * @return Una ArrayList con los usuarios registrados 
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    /**
     * Metodo para obtener servicios 
     * @return Una ArrayList con los servicios registrados
     */
    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    /** obtiene la cantidad de clientes
     * @return la cantidad de clientes existentes
     */
    public int getClientQ(){
        int tmp=0;
        for (Usuario usr : usuarios) {
            if(usr.getTipo()!= null && usr.getTipo().equals(TipoUsuario.CLIENTE)){
                tmp+=1;
            }
        }
        return tmp;
    }
    /**
     * Metodo para obtener la identificacion de los usuarios
     * @return una lista con las identificaciones 
     */
    public String[] userIDs(){
        String tmp[] = new String[getClientQ()];
        int x=0;
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getTipo()!= null && usuarios.get(i).getTipo().equals(TipoUsuario.CLIENTE)){
                tmp[x]=usuarios.get(i).getID();
                x++;
            }
        }
        return tmp;
    }
    /**
     * Devuelve los vehiculos de un usuario
     * @param id la identificacion del usuario a consultar
     * @return una lista con los vehiculos 
     */
    public String[] getUserVehicles(String id){
        for (Usuario usr : usuarios) {
            if(usr.getID().equals(id)){
                if(usr.getVehiculos().size()==0){
                    return null;
                }
                String tmp[]=new String[usr.getVehiculos().size()];
                for (int i = 0; i < usr.getVehiculos().size(); i++) {
                    tmp[i]=usr.getVehiculos().get(i).getPlaca();
                }
                return tmp;
            }
        }
        return null;
    }
    /**
     * Metodo para registrar un usuario
     * @param id identificacion del usuario
     * @param pass contrasenha del usuario
     * @return true si se logro / false de lo contrario
     */
    public boolean signUp(String id,String pass){
        for (Usuario usr : usuarios) {
            if(usr.getTipo().equals(TipoUsuario.EMPLEADO)&&(usr.getUserName().equals(id)||usr.getID().equals(id))&&usr.getUserPass().equals(pass))
            return true;
        }
        return false;
    }
}