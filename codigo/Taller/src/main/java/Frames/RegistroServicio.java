/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frames;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import taller.BD;
import taller.Servicio;

/**
 *
 * @author ttc46
 */
public class RegistroServicio extends javax.swing.JFrame {
    public BD bd;
    private final String direccion= "C:\\Users\\Usuario\\Documents\\NetBeansProjects\\Taller\\src\\main\\java\\data.json";

    /**
     * Creates new form RegistroServicio
     * @param bd
     */
    public RegistroServicio(BD bd) {
        this.bd=bd;
        initComponents();
        try {
            getIdentificaciones();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.jCheckBox1.setVisible(false);
            this.jCheckBox2.setVisible(false);
            this.jCheckBox3.setVisible(false);
            this.jCheckBox4.setVisible(false);
            this.jCheckBox5.setVisible(false);
            this.jCheckBox6.setVisible(false);
            this.jCheckBox7.setVisible(false);
            this.jCheckBox8.setVisible(false);
            this.jCheckBox9.setVisible(false);
            this.jCheckBox10.setVisible(false);
            this.jCheckBox11.setVisible(false);
            this.jCheckBox12.setVisible(false);
            this.jCheckBoxPoliza.setVisible(false);
            this.jLabelPartes.setVisible(false);
            this.jLabelCaso.setVisible(false);
            this.jTextFieldCaso.setVisible(false);
            this.jLabelProb.setVisible(false);
            this.jTextProb.setVisible(false);
    }
    private void getIdentificaciones() throws FileNotFoundException{
        jComboBoxUser.removeAllItems();
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Clientes").getAsJsonArray();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (new BD().ligadoA(indicador.get("Identificacion").getAsString(), "Vehiculos", "Identificacion")){
                jComboBoxUser.addItem(indicador.get("Identificacion").getAsString());
            }
        }
        if (jComboBoxUser==null){
            JOptionPane.showMessageDialog(null, "No hay clientes con vehiculos", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    private void getVehiculos() throws FileNotFoundException{
        JsonObject obj= new JsonParser().parse(new FileReader(direccion)).getAsJsonObject();
        JsonArray marcas= obj.get("Vehiculos").getAsJsonArray();
        jComboBoxVehicle.removeAllItems();
        for (JsonElement marca : marcas){
            JsonObject indicador= marca.getAsJsonObject();
            if (jComboBoxUser.getSelectedItem()!=null && jComboBoxUser.getSelectedItem().toString().equals(indicador.get("Identificacion").getAsString())){
                jComboBoxVehicle.addItem(indicador.get("Placa").getAsString());
            }
        }
        if (jComboBoxUser.getSelectedItem()!= null&&jComboBoxVehicle==null){
            JOptionPane.showMessageDialog(null, "No hay vehiculos ligados", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
    /** obtiene las partes con checks;
     * @return null si no hay ninguna seleccionada, Arraylist<String> si hay
     */
    private ArrayList<String> getSelectedChecks(){
        ArrayList<String> tmp = new ArrayList<String>();

        // https://media.giphy.com/media/zjUQlOdrAP02dJ64gJ/giphy.gif 
        if(jCheckBox1.isSelected()||jCheckBox2.isSelected()||jCheckBox3.isSelected()||jCheckBox4.isSelected()||jCheckBox5.isSelected()||jCheckBox6.isSelected()||jCheckBox7.isSelected()||jCheckBox8.isSelected()||jCheckBox9.isSelected()||jCheckBox10.isSelected()||jCheckBox11.isSelected()||jCheckBox12.isSelected()){
            if(jCheckBox1.isSelected()){
                tmp.add(jCheckBox1.getText());
            }
            if(jCheckBox2.isSelected()){
                tmp.add(jCheckBox2.getText());
            }
            if(jCheckBox3.isSelected()){
                tmp.add(jCheckBox3.getText());
            }
            if(jCheckBox4.isSelected()){
                tmp.add(jCheckBox4.getText());
            }
            if(jCheckBox5.isSelected()){
                tmp.add(jCheckBox5.getText());
            }
            if(jCheckBox6.isSelected()){
                tmp.add(jCheckBox6.getText());
            }
            if(jCheckBox7.isSelected()){
                tmp.add(jCheckBox7.getText());
            }
            if(jCheckBox8.isSelected()){
                tmp.add(jCheckBox8.getText());
            }
            if(jCheckBox9.isSelected()){
                tmp.add(jCheckBox9.getText());
            }
            if(jCheckBox10.isSelected()){
                tmp.add(jCheckBox10.getText());
            }
            if(jCheckBox11.isSelected()){
                tmp.add(jCheckBox11.getText());
            }
            if(jCheckBox12.isSelected()){
                tmp.add(jCheckBox12.getText());
            }
            return tmp;
        }
        else{
            JOptionPane.showMessageDialog(this, "Seleccione almenos una parte","ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")  
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tServicioComboBox = new javax.swing.JComboBox<>();
        butSalir = new javax.swing.JButton();
        jLabelCedula = new javax.swing.JLabel();
        jLabelPlacavehiculo = new javax.swing.JLabel();
        jLabelDesc = new javax.swing.JLabel();
        jLabelPartes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextDesc = new javax.swing.JTextPane();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        jCheckBoxPoliza = new javax.swing.JCheckBox();
        jLabelProb = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextProb = new javax.swing.JTextPane();
        jLabelCaso = new javax.swing.JLabel();
        jLabelCosto = new javax.swing.JLabel();
        jFormattedTextCosto = new javax.swing.JFormattedTextField();
        jTextFieldCaso = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabelFR = new javax.swing.JLabel();
        jLabelFE = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBoxUser = new javax.swing.JComboBox<>();
        jComboBoxVehicle = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tServicioComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Eliga una opcion", "Mecanica General", "Enderezado y Pintura" }));
        tServicioComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tServicioComboBoxActionPerformed(evt);
            }
        });

        butSalir.setText("Salir");
        butSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        butSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSalirActionPerformed(evt);
            }
        });

        jLabelCedula.setText("Cedula del cliente");

        jLabelPlacavehiculo.setText("Placa del Vehiculo");

        jLabelDesc.setText("Descripcion");

        jLabelPartes.setText("Partes");

        jScrollPane1.setViewportView(jTextDesc);

        jCheckBox1.setText("Bumper");

        jCheckBox2.setText("Capo");

        jCheckBox3.setText("Cajuela");

        jCheckBox4.setText("Puerta Derecha");

        jCheckBox5.setText("Puerta Izquierda");

        jCheckBox6.setText("Techo");

        jCheckBox7.setText("Bumper trasero");

        jCheckBox8.setText("Guarda Fangos");

        jCheckBox9.setText("Panel Trasero");

        jCheckBox10.setText("Estribo");

        jCheckBox11.setText("Espejos");

        jCheckBox12.setText("Tapa Cajuela");

        jCheckBoxPoliza.setText("Poliza");

        jLabelProb.setText("Descripcion Problema ");

        jScrollPane2.setViewportView(jTextProb);

        jLabelCaso.setText("Numero de caso");

        jLabelCosto.setText("Costo");

        jFormattedTextCosto.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0"))));
        jFormattedTextCosto.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        jFormattedTextCosto.setMinimumSize(new java.awt.Dimension(150, 22));
        jFormattedTextCosto.setPreferredSize(new java.awt.Dimension(150, 22));

        jTextFieldCaso.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        jTextFieldCaso.setMinimumSize(new java.awt.Dimension(150, 22));
        jTextFieldCaso.setPreferredSize(new java.awt.Dimension(150, 22));

        jLabelFR.setText("Fecha recibido");

        jLabelFE.setText("Fecha de entrega");

        jButton1.setText("Registrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBoxUser.setMinimumSize(new java.awt.Dimension(125, 22));
        jComboBoxUser.setPreferredSize(new java.awt.Dimension(125, 22));
        jComboBoxUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUserActionPerformed(evt);
            }
        });

        jComboBoxVehicle.setMinimumSize(new java.awt.Dimension(125, 22));
        jComboBoxVehicle.setPreferredSize(new java.awt.Dimension(125, 22));
        jComboBoxVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVehicleActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCaso)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPartes)
                            .addComponent(jLabelCosto)
                            .addComponent(jLabelFR))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxPoliza))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox4)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBox10))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBox3)
                                                .addComponent(jCheckBox2))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jCheckBox6)
                                                    .addGap(64, 64, 64))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                    .addComponent(jCheckBox5)
                                                    .addGap(18, 18, 18))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(38, 38, 38)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBox8)
                                                .addComponent(jCheckBox9))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jCheckBox12)
                                                .addComponent(jCheckBox11)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelFE)
                                            .addGap(28, 28, 28)
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(butSalir))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabelProb)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tServicioComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelCedula)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addComponent(jLabelPlacavehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tServicioComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCedula)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelPlacavehiculo)
                        .addComponent(jComboBoxVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabelDesc))
                    .addComponent(jLabelProb)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabelPartes))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox7)
                            .addComponent(jCheckBox10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox8)
                            .addComponent(jCheckBox11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCheckBox3)
                                .addComponent(jCheckBox6))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jCheckBox9)
                                .addComponent(jCheckBox12)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCaso)
                    .addComponent(jTextFieldCaso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxPoliza))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCosto)
                            .addComponent(jFormattedTextCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelFR, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFE, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(butSalir)
                        .addComponent(jButton1)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tServicioComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tServicioComboBoxActionPerformed
        // TODO add your handling code here:
        if(this.tServicioComboBox.getSelectedIndex()==0){
            this.jCheckBox1.setVisible(false);
            this.jCheckBox2.setVisible(false);
            this.jCheckBox3.setVisible(false);
            this.jCheckBox4.setVisible(false);
            this.jCheckBox5.setVisible(false);
            this.jCheckBox6.setVisible(false);
            this.jCheckBox7.setVisible(false);
            this.jCheckBox8.setVisible(false);
            this.jCheckBox9.setVisible(false);
            this.jCheckBox10.setVisible(false);
            this.jCheckBox11.setVisible(false);
            this.jCheckBox12.setVisible(false);
            this.jCheckBoxPoliza.setVisible(false);
            this.jLabelPartes.setVisible(false);
            this.jLabelCaso.setVisible(false);
            this.jTextFieldCaso.setVisible(false);
            this.jLabelProb.setVisible(false);
            this.jTextProb.setVisible(false);
        }
        else if (this.tServicioComboBox.getSelectedIndex()==1){
            this.jCheckBox1.setVisible(false);
            this.jCheckBox2.setVisible(false);
            this.jCheckBox3.setVisible(false);
            this.jCheckBox4.setVisible(false);
            this.jCheckBox5.setVisible(false);
            this.jCheckBox6.setVisible(false);
            this.jCheckBox7.setVisible(false);
            this.jCheckBox8.setVisible(false);
            this.jCheckBox9.setVisible(false);
            this.jCheckBox10.setVisible(false);
            this.jCheckBox11.setVisible(false);
            this.jCheckBox12.setVisible(false);
            this.jCheckBoxPoliza.setVisible(false);
            this.jLabelPartes.setVisible(false);
            this.jLabelCaso.setVisible(false);
            this.jTextFieldCaso.setVisible(false);
            this.jLabelProb.setVisible(true);
            this.jTextProb.setVisible(true);
        }
        else{
            this.jCheckBox1.setVisible(true);
            this.jCheckBox2.setVisible(true);
            this.jCheckBox3.setVisible(true);
            this.jCheckBox4.setVisible(true);
            this.jCheckBox5.setVisible(true);
            this.jCheckBox6.setVisible(true);
            this.jCheckBox7.setVisible(true);
            this.jCheckBox8.setVisible(true);
            this.jCheckBox9.setVisible(true);
            this.jCheckBox10.setVisible(true);
            this.jCheckBox11.setVisible(true);
            this.jCheckBox12.setVisible(true);
            this.jCheckBoxPoliza.setVisible(true);
            this.jLabelPartes.setVisible(true);
            this.jLabelCaso.setVisible(true);
            this.jTextFieldCaso.setVisible(true);
            this.jLabelProb.setVisible(false);
            this.jTextProb.setVisible(false);
        }
    }//GEN-LAST:event_tServicioComboBoxActionPerformed

    private void butSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butSalirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // revisar y registrar
        if(this.tServicioComboBox.getSelectedIndex()==1){       //opcion de mecanica
            if(!this.jTextDesc.getText().isEmpty()&&!this.jTextProb.getText().isEmpty()&&!this.jFormattedTextCosto.getText().isEmpty()&&this.jDateChooser1.getDate()!=null&&this.jDateChooser2.getDate()!=null){
                
                bd.addService(new Servicio(bd.getVehiculoByuser((String)jComboBoxUser.getSelectedItem(), (String)jComboBoxVehicle.getSelectedItem()), this.jTextFieldCaso.getText(), this.jTextDesc.getText(), this.jTextProb.getText(),this.jFormattedTextCosto.getText(),this.jDateChooser1.getDate().toString(), this.jDateChooser2.getDate().toString()));
                this.dispose();
            }
            else{
                JOptionPane.showMessageDialog(this, "Llene todos los campos","ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (this.tServicioComboBox.getSelectedIndex()==2){     //opcion de enderezado
            if(!this.jTextDesc.getText().isEmpty()&&!this.jFormattedTextCosto.getText().isEmpty()&&this.jDateChooser1.getDate()!=null&&this.jDateChooser2.getDate()!=null){
                if(getSelectedChecks()!=null){
                     bd.addService(new Servicio(bd.getVehiculoByuser((String)jComboBoxUser.getSelectedItem(), (String)jComboBoxVehicle.getSelectedItem()),(String)jComboBoxUser.getSelectedItem(),jTextDesc.getText(),getSelectedChecks(),jCheckBoxPoliza.isSelected(),jTextFieldCaso.getText(),jFormattedTextCosto.getText(),jDateChooser1.getDate().toString(),jDateChooser2.getDate().toString()));
                    this.dispose();
                    }
            }
            else{
                JOptionPane.showMessageDialog(this, "Llene todos los campos","ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Seleccione una opcion de servicio","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVehicleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxVehicleActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            getIdentificaciones();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxUserActionPerformed(java.awt.event.ActionEvent evt) {                                              
        try {
            getVehiculos();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RegistroServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroServicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistroServicio(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butSalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JCheckBox jCheckBoxPoliza;
    private javax.swing.JComboBox<String> jComboBoxUser;
    private javax.swing.JComboBox<String> jComboBoxVehicle;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JFormattedTextField jFormattedTextCosto;
    private javax.swing.JLabel jLabelCaso;
    private javax.swing.JLabel jLabelCedula;
    private javax.swing.JLabel jLabelCosto;
    private javax.swing.JLabel jLabelDesc;
    private javax.swing.JLabel jLabelFE;
    private javax.swing.JLabel jLabelFR;
    private javax.swing.JLabel jLabelPartes;
    private javax.swing.JLabel jLabelPlacavehiculo;
    private javax.swing.JLabel jLabelProb;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextDesc;
    private javax.swing.JTextField jTextFieldCaso;
    private javax.swing.JTextPane jTextProb;
    private javax.swing.JComboBox<String> tServicioComboBox;
    // End of variables declaration//GEN-END:variables
}
