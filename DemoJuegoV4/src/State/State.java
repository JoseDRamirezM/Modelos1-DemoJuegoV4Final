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
public interface State {
    final int ARCO = 0;
    final int ESPADA = 1;
    public int handle(int state);
}
