/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;

import Graficos.Sprite;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author USUARIO
 */
public class EstrategiaAdelanteHumano extends Strategy {

    @Override
    public void teclaPresionada(Sprite s) {
            s.requestFocus();
            try {
            Robot robot = new Robot();

            // Simulate a key press 
            robot.keyRelease(KeyEvent.VK_W);
            robot.keyPress(KeyEvent.VK_UP);                   

            } catch (AWTException e) {
                e.printStackTrace();
            }
    }
    
}
