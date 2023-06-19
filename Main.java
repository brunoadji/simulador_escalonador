package simulador_escalonador;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.JFrame;

class Main extends Canvas implements Runnable{
    private JFrame jFrame;

    public static final int WIDTH =  1280;
    public static final int HEIGHT = 720;

    private OS os;

    int pIndex=0;

    int clk = 0;

    Map<String,String[]> lines = new HashMap<String,String[]>();


    public Main() throws IOException{
        jFrame = new JFrame("Tela");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(this);
        jFrame.setVisible(true);

        Dimension size = new Dimension(WIDTH, HEIGHT);
        setPreferredSize(size);
        setBackground(Color.black);
        jFrame.setSize(WIDTH, HEIGHT);

        os = new OS();
        read();
    }

    public void read() throws IOException{
        BufferedReader buffRead = new BufferedReader(
            new FileReader("./simulador_escalonador/entries.txt"));

		String[] line = null;
		while (true) {
			String str = buffRead.readLine();

			if (str == null) 
				break;

            line = str.strip().replaceAll(" ", "").split(",");

            String index = line[0];

            lines.put(index, line);
		}
		buffRead.close();
        
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fill3DRect(0, 0, WIDTH, HEIGHT, false);
        
        g.setColor(Color.blue);
        g.drawString("clock: "+clk, 500, 20);

        os.draw(g);
    }

    @Override
    public void run() {
        int i = 0;

        String[] line = null;

        while(true){

            if(lines.containsKey(i+""))
                line = lines.get(i+"");

            if(line != null){
                os.createProcess("P"+pIndex, Integer.parseInt(line[0]), Integer.parseInt(line[1]), 
                Integer.parseInt(line[2]),Integer.parseInt(line[3]),Integer.parseInt(line[4]),
                Integer.parseInt(line[5]), Integer.parseInt(line[6]));
                pIndex++;
                line = null;
            }

            os.executeCicle();
            repaint();

            try{
                Thread.sleep(4000);
            }catch(Exception e){
                System.out.println(e);
            }
            System.out.println("esperando 2 segundos");

            i++;
            clk++;
        }

    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        Main main = new Main();
        main.run();
    }
}