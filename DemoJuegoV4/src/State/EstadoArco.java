/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

/**
 *
 * @author USUARIO
 */
public class EstadoArco implements State {

    @Override
    public int handle(int state) {
        state = State.ARCO;
        return state;
    }
    
}
