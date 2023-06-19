package simulador_escalonador;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JFrame;

class Main extends Canvas implements Runnable{

    private JFrame jFrame;

    public static final int WIDTH =  1280;
    public static final int HEIGHT = 720;

    private OS os;

    public Main(){
        jFrame = new JFrame("Tela");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(this);
        jFrame.setVisible(true);

        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setBackground(Color.black);
        jFrame.setSize(WIDTH, HEIGHT);

        os = new OS();
        //os.createProcess("P1",0, 0, 10, 2504, 0, 4, 6);
        //os.createProcess("P2",0, 0, 10, 2504, 0, 4, 6);
        os.createProcess("Px",0, 1, 10, 2504, 0, 4, 6);  
        
        for(int i=0; i<3; i++){
            os.createProcess("P"+i,0, 0, 3, 2504, 0, 4, 6);  
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fill3DRect(0, 0, WIDTH, HEIGHT, false);

        os.draw(g);
    }

    @Override
    public void run() {
        //os.executeCicle();

        while(true){
            os.executeCicle();
            repaint();

            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("esperando 3 segundos");
        }

    }

    public static void main(String[] args){
        Main main = new Main();
        main.run();
    }
}