package simulador_escalonador;

import java.awt.Graphics;

public class Page implements Drawnable{
    private ProcessOS process;
    private Frame frame;

    public Page(ProcessOS process) {
        this.process = process;
        frame = null;
    }

    public ProcessOS getProcess() {
        return this.process;
    }

    public Frame getFrame() {
        return this.frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public void clean() {
        this.frame.freeFrame();
        this.process = null;
    }

    @Override
    public void draw(Graphics g){}

    public void draw(Graphics g, int x, int y) {
        g.drawString(process.getID(), x,y);
    }
}
