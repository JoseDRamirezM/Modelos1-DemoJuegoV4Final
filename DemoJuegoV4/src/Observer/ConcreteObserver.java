/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

/**
 *
 * @author USUARIO
 */
public class ConcreteObserver implements Observer {
    private String ataque;

    
    
    private int observerState;

    public void setObserverState(int observerState) {
        this.observerState = observerState;
    }

    public int getObserverState() {
        return observerState;
    }
    
    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    public String getAtaque() {
        return ataque;
    }

    @Override
    public void update(String ataque) {
        this.ataque = ataque;       
    }
    
    
    
    
    
}
