/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;

import CadenaResponsabilidad.Handler;
import CadenaResponsabilidad.HandlerElfo;
import CadenaResponsabilidad.HandlerHumano;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author USUARIO
 */
public class Frame extends JFrame implements ActionListener {
    
    private String texto;
    private JLabel label;
    private JButton boton1;
    private JButton boton2;
    private ActionListener l;
    private Sprite s;
    private Handler[] manejadores;
    
    public Frame(Sprite s){
        texto = "<HTML>  Instrucciones<br>"
                + "  Para controlar al humano utilizar las flechas y espacio para  atacar <br>"
                + "  Para controlar al elfo utilizar WASD y x para atacar<br><br>"
                + "  El patron decorador se ve reflejado en como cambia el ataque"
                + "  del personaje al estar por encima del arco o bajo el mismo<br><br>"
                + "  El patron prototype se ve reflejado en el control de poblaciones de personajes  </HTML>";              
        label = new JLabel();
        label.setText(texto);
        boton1 = new JButton("Accion Humano");
        boton2 = new JButton("Accion Elfo");
        this.add(label, BorderLayout.CENTER);        
        this.boton1.addActionListener(l);
        this.boton2.addActionListener(l);        
        this.boton1.addActionListener(this);
        this.boton2.addActionListener(this);
        this.add(boton1, BorderLayout.NORTH);
        this.add(boton2, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Juego");
        this.setResizable(false);
        this.setSize(500,300);
        this.setVisible(true);
        this.s = s;
        manejadores = new Handler[2];
        manejadores[0] = new HandlerElfo();
        manejadores[1] = new HandlerHumano();
        
        for(int i=0; i<manejadores.length -1; i++){
            manejadores[i].setSucesor(manejadores[i+1]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == boton1){
            manejadores[0].moverAdelante(1, s);                        
        }else {
            manejadores[1].moverAdelante(0, s);
        }
    }
                    
}
    
