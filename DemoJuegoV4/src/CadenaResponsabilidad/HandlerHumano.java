/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CadenaResponsabilidad;

import Graficos.Sprite;
import Strategy.EstrategiaAdelanteHumano;

/**
 *
 * @author USUARIO
 */
public class HandlerHumano extends Handler {

    
    @Override
    public void moverAdelante(int opt, Sprite s) {
        if(opt == 0){
            super.e = new EstrategiaAdelanteHumano();
            e.teclaPresionada(s);
        }else {
            moverAdelante(opt, s);
        }
    }
    
    
}
