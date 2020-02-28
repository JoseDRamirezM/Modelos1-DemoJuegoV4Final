/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graficos;


import Control.Teclado;
import Observer.ConcreteObserver;
import State.EstadoArco;
import State.EstadoEspada;
import State.State;
import decorator.Component;
import decorator.ComponenteReal;
import decorator.WrapperUno;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author USUARIO
 */
public class Sprite extends JFrame implements Runnable {

    private int anchoVentana = 1000;
    private int altoVentana = 600;  
    private final int ARCO = 0;
    private final int ESPADA =1;
    
    
    int x = 0,y = 0, x1 = 0, y1 = 0, velx = 0, vely = 0, velx1 = 0, vely1 = 0;
    
    private Image img;
    private Image img2;
    private Image img3;
    private Image imgAux;
    private Thread hilo;
    private BufferedImage bi;     
    private int incremento = 0;
    private static Teclado t;
    int i = 0;
    int posix = 0, posix1 = 0;
    int posiy = 64, posiy1 = 64;
    int frames = 8;
    
    int numHumanos;
    int numElfos;
    int aumentoPosi;
    int my;
    int mx;
    Component real;
    Component wrapper;
    Graphics2D g2d;
    
    ConcreteObserver observerElfo; 
    ConcreteObserver observerHumano;
    private String ataqueHumano;
    private String ataqueElfo;
    private int stateHumano;
    private int stateElfo;
    
    private EstadoEspada estadoEspada;
    private EstadoArco estadoArco;
    
    public Sprite(String dir1, String dir2, int numHumanos, int numElfos ){        
        this.setSize(anchoVentana, altoVentana);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Juego");
        this.setResizable(false);
        t = new Teclado();
        addKeyListener(t);
        hilo = new Thread(this);
        bi = new BufferedImage(anchoVentana, altoVentana, BufferedImage.TYPE_INT_RGB);        
        Toolkit herramienta;
        herramienta = Toolkit.getDefaultToolkit();
        img = herramienta.getImage(getClass().getResource(dir1));
        img2 = herramienta.getImage(getClass().getResource(dir2));
        imgAux = herramienta.getImage(getClass().getResource("/img/espada.png")); 
        img3 = imgAux.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH );
        hilo.start();
        this.numHumanos =  numHumanos;
        this.numElfos = numElfos;
        real = new ComponenteReal();
        wrapper = new WrapperUno(real); 
        stateHumano = State.ARCO;
        stateElfo = State.ARCO;
        ataqueHumano = ("Ataque Humano: " + stateHumano);
        ataqueElfo = ("Ataque Elfo: " + stateElfo);
        estadoEspada = new EstadoEspada();
        estadoArco = new EstadoArco();
        observerElfo = new ConcreteObserver();
        observerHumano = new ConcreteObserver();
        
        notifyObserverElfo();
        notifyObserverHumano();
        definirEstado();
        
        this.setVisible(true);
        
        
    }
    
    @Override
    public void paint(Graphics g) { 
        
        
               
        g.drawImage(bi,0 ,0 ,this);        
        g2d = (Graphics2D) bi.getGraphics();
        g2d.fillRect(0, 0 , anchoVentana, altoVentana);
        
        labelAtaqueHumano();
        labelAtaqueElfo();
        
        mx = (incremento%frames)*64;
        my = i; /*64;*///(incremento/13)*64; //multiplos de 64 de 0 a 1020
        aumentoPosi = 40;
              
                
        g2d.drawImage(img, 200+x, 200+y, 200+64+x ,200+64+y, posix, posix, posiy, posiy ,this);
        for(int j = 0; j < numElfos -1; j++){
            g2d.drawImage(img, 200+x+aumentoPosi, 200+y, 200+64+x+aumentoPosi ,200+64+y, posix, posix, posiy, posiy ,this);
            aumentoPosi += 40;
        }
         
        aumentoPosi = 40;
        g2d.drawImage(img2, 50+x1, 50+y1, 50+64+x1, 50+64+y1, posix1, posix1, posiy1, posiy1 ,this);
        for(int j = 0; j < numHumanos - 1; j++){
            g2d.drawImage(img2, 50+x1+aumentoPosi, 50+y1, 50+64+x1+aumentoPosi , 50+64+y1, posix1, posix1, posiy1, posiy1 ,this);
            aumentoPosi += 40;
        }                
        g2d.drawImage(img3, 500, 300, this);
        if((200+x)> anchoVentana-20 || (200+y)> altoVentana-20 || (200+x) < 20 || (200+y) < 20
                || (50+x1)> anchoVentana-5 || (50+y1)> altoVentana-5 || (50+x1) < 5 || (50+y1) < 5){
            x=0;
            y=0;
            x1 = 0;
            y1 = 0;
        }
        
        this.repaint(); 
        
        if(t.arriba){          
            avanzar(); 
            
        }else if(t.abajo){            
            aumentoPosi = 40;
            vely = 8;
            velx = 0;
          
            posix = 128;
            posiy = 192;
            i = 384;
            
            g2d.drawImage(img, 200+x, 200+y, 200+64+x ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this); 
            for(int j = 0; j < numElfos -1; j++){
                g2d.drawImage(img, 200+x+aumentoPosi, 200+y, 200+64+x+aumentoPosi ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this); 
                aumentoPosi += 40;
            }
        this.repaint();
            
        } else if(t.derecha){
            aumentoPosi = 40;
            vely = 0;
            velx = 8;          
            posix = 192;
            posiy = 256;
            i = 448;
            g2d.drawImage(img, 200+x, 200+y, 200+64+x ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this);
            for(int j = 0; j < numElfos -1; j++){
                g2d.drawImage(img, 200+x+aumentoPosi, 200+y, 200+64+x+aumentoPosi ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this); 
                aumentoPosi += 40;
            }
            this.repaint(); 
            
        }else if(t.izquierda){
            aumentoPosi = 40;
            vely = 0;
            velx = -8;
           
            posix = 64;
            posiy = 128;
            i = 320;
            g2d.drawImage(img, 200+x, 200+y, 200+64+x ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this);
            for(int j = 0; j < numElfos -1; j++){
                g2d.drawImage(img, 200+x+aumentoPosi, 200+y, 200+64+x+aumentoPosi ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this); 
                aumentoPosi += 40;
            }
            this.repaint(); 
            
        }else if(t.atacar){
            int[] v;
            aumentoPosi = 40;
            if(stateElfo == ESPADA){
                v = real.doThis(frames, posix, posiy, i);
                frames = v[0];
                posix = v[1];
                posiy = v[2];
                i = v[3];
            }else {
                v = wrapper.doThis(frames, posix, posiy, i);
                frames = v[0];
                posix = v[1];
                posiy = v[2];
                i = v[3];
            }
            g2d.drawImage(img, 200+x, 200+y, 200+64+x ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this); 
            for(int j = 0; j < numElfos -1; j++){
                g2d.drawImage(img, 200+x+aumentoPosi, 200+y, 200+64+x+aumentoPosi ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this); 
                aumentoPosi += 40;
            }
            this.repaint(); 
            
        }else if(t.arriba1){
            avanzar1();
            
        }else if(t.abajo1){
            aumentoPosi = 40;
            vely1 = 8;
            velx1 = 0;          
            posix1 = 128;
            posiy1 = 192;
            i = 384;
            g2d.drawImage(img2, 50+x1, 50+y1, 50+64+x1 , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this);
            for(int j = 0; j < numHumanos -1 ; j++){
                g2d.drawImage(img2, 50+x1+aumentoPosi, 50+y1, 50+64+x1+aumentoPosi , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this); 
                aumentoPosi += 40;
            }
            this.repaint();
            
        }else if(t.derecha1){
            aumentoPosi = 40;
            vely1 = 0;
            velx1 = 8;          
            posix1 = 192;
            posiy1 = 256;
            i = 448;
            g2d.drawImage(img2, 50+x1, 50+y1, 50+64+x1 , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this);
            for(int j = 0; j < numHumanos -1 ; j++){
                g2d.drawImage(img2, 50+x1+aumentoPosi, 50+y1, 50+64+x1+aumentoPosi , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this); 
                aumentoPosi += 40;
            }
            this.repaint();
            
        }else if(t.izquierda1){
            aumentoPosi = 40;
            vely1 = 0;
            velx1 = -8;
           
            posix1 = 64;
            posiy1 = 128;
            i = 320;
            g2d.drawImage(img2, 50+x1, 50+y1, 50+64+x1 , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this);
            for(int j = 0; j < numHumanos -1 ; j++){
                g2d.drawImage(img2, 50+x1+aumentoPosi, 50+y1, 50+64+x1+aumentoPosi , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this); 
                aumentoPosi += 40;
            }
            this.repaint();
            
        }else if(t.atacar1){
            aumentoPosi = 40;
            int[] v;
            aumentoPosi = 40;
            if(stateHumano == ESPADA){
                v = real.doThis(frames, posix, posiy, i);
                frames = v[0];
                posix = v[1];
                posiy = v[2];
                i = v[3];
            }else {
                v = wrapper.doThis(frames, posix, posiy, i);
                frames = v[0];
                posix = v[1];
                posiy = v[2];
                i = v[3];
            }           
            g2d.drawImage(img2, 50+x1, 50+y1, 50+64+x1 , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this);
            for(int j = 0; j < numHumanos -1 ; j++){
                g2d.drawImage(img2, 50+x1+aumentoPosi, 50+y1, 50+64+x1+aumentoPosi , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this); 
                aumentoPosi += 40;
            }
            this.repaint();
            
        }       
    }   
    
    public void labelAtaqueHumano(){
        FontRenderContext frc = g2d.getFontRenderContext();
        Font font1 = new Font("Courier", Font.BOLD, 24);
        String str1 = new String("Arma Humano(s):" + ataqueHumano);
        TextLayout tl = new TextLayout(str1, font1, frc);
        g2d.setColor(Color.gray);
        tl.draw(g2d, 400, 500);
    }
    
    public void labelAtaqueElfo(){
        FontRenderContext frc = g2d.getFontRenderContext();
        Font font1 = new Font("Courier", Font.BOLD, 24);
        String str1 = new String("Arma Elfo(s):" + ataqueElfo);
        TextLayout tl = new TextLayout(str1, font1, frc);
        g2d.setColor(Color.gray);
        tl.draw(g2d, 400, 400);
    }
    
    public void definirEstado(){
        if((200+y)> (altoVentana/2)){
            stateElfo = estadoEspada.handle(stateElfo);   
            ataqueElfo = observerElfo.getAtaque();
        }else{ 
            stateElfo = State.ARCO;
            ataqueElfo = observerElfo.getAtaque();
        }
        if ((50+y1)> (altoVentana/2)){
            stateHumano = estadoEspada.handle(stateHumano); 
            ataqueHumano = observerHumano.getAtaque();
        }else{
            stateHumano = State.ARCO;
            ataqueHumano = observerHumano.getAtaque();
        }
        
    }
    
    public void avanzar(){
        aumentoPosi = 40;
        vely = -8;
        velx = 0;             
        posix = 0;
        posiy = 64;           
        i = 256;
        g2d.drawImage(img, 200+x, 200+y, 200+64+x ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this); 
        for(int j = 0; j < numElfos -1; j++){
            g2d.drawImage(img, 200+x+aumentoPosi, 200+y, 200+64+x+aumentoPosi ,200+64+y ,mx ,my ,mx+64 ,my+64 ,this); 
            aumentoPosi += 40;
        }
        this.repaint();  
    }
    
    public void avanzar1(){
        aumentoPosi = 40;
        vely1 = -8;
        velx1 = 0;             
        posix1 = 0;
        posiy1 = 64;
        i = 256;
        g2d.drawImage(img2, 50+x1, 50+y1, 50+64+x1 , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this);
        for(int j = 0; j < numHumanos -1 ; j++){
            g2d.drawImage(img2, 50+x1+aumentoPosi, 50+y1, 50+64+x1+aumentoPosi , 50+64+y1 ,mx ,my ,mx+64 ,my+64 ,this); 
            aumentoPosi += 40;
        }
        this.repaint();
    }
    
    public void attach(ConcreteObserver observerElfo,
                       ConcreteObserver observerHumano){
        
        this.observerElfo = observerElfo;
        this.observerHumano = observerHumano;
    }
    
    public void detach(){
        this.observerElfo = null;
        this.observerHumano = null;
    }
    
    public void notifyObserverElfo(){        
        observerElfo.update(regresarAtaqueElfo());
    }
    
    public void notifyObserverHumano(){
        observerHumano.update(regresarAtaqueHumano());
    }
    
    
    
    public String regresarAtaqueElfo(){
        if((200+y) > (altoVentana/2)){
            return "Espada elfo";
        }else if((200+y) < (altoVentana/2)) {
            return "Arco elfo";
        } else return null;   
    }
    
    public String regresarAtaqueHumano(){
        if((50+y1)> (altoVentana/2)){
            return "Espada humano";
        }else if ((50+y1) < (altoVentana/2)) {
            return "Arco Humano";
        }else return null;
    }
    

    @Override
    public void run() {        
        requestFocusInWindow();
        while(true){
            try { 
            t.actualizar();
                          
            if(t.estado && !t.atacar){
                x += velx;
                y += vely;                
            }else if(t.estado1 && !t.atacar1){
                x1 += velx1;
                y1 += vely1;
            }
            notifyObserverElfo();
            notifyObserverHumano();
            definirEstado();  
            Thread.sleep(50);                        
            } catch (InterruptedException ex){
                Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
            }
            incremento++; 
               
            
            if(incremento > 221){
                incremento = 0;
            }
            
        }
    }
        
}
