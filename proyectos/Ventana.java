/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.exp;

import javax.swing.*;

/**
 *
 * @author Usuario
 */
public class Ventana extends JFrame {
    public JPanel panel= new JPanel();
    public static void main(String[] args){
        Ventana t1 = new Ventana();
    }    
    public Ventana(){
        this.setTitle("Ventana");
        this.setSize(450,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        this.setVisible(true);
    }
    
}
