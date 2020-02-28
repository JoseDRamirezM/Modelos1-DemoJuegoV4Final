/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadenaResponsabilidad;

import Graficos.Sprite;
import Strategy.EstrategiaAdelanteElfo;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class HandlerElfo extends Handler {

    @Override
    public void moverAdelante(int opt, Sprite s) {
        if(opt == 1){
            super.e = new EstrategiaAdelanteElfo();
            e.teclaPresionada(s);
        }else {
            moverAdelante(opt, s);
        }       
    }
    
}
