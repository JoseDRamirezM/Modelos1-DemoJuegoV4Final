/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadenaResponsabilidad;

import Graficos.Sprite;
import Strategy.Strategy;


/**
 *
 * @author USUARIO
 */
public abstract class Handler {
    Handler sucesor;
    Sprite s;
    Strategy e;
    
    public abstract void moverAdelante(int opt, Sprite s);
    
    public Handler getSucesor(){
        return this.sucesor;
    }
    
    public void setSucesor(Handler sucesor){
        this.sucesor = sucesor;
    }
    
}
