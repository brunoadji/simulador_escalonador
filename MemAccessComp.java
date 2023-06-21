package simulador_escalonador;

import java.awt.Color;
import java.awt.Graphics;

public class MemAccessComp implements Drawnable{
    
    private ProcessListGroup queues;
    private int diskIndex;

    private ProcessOS currentProcess = null;

    private int count = 0;

    public MemAccessComp(ProcessListGroup queues, int diskIndex){
        this.queues = queues;
        this.diskIndex = diskIndex;
    }

    public void getProcess(){
        if(currentProcess == null)
            currentProcess = queues.getFromBlockQueue(diskIndex);
    }

    public void access(){
        if(currentProcess == null) return;

        count++;

        System.out.println(count+"bloqueou");

        if(currentProcess.getIOduration() <= count){
            currentProcess.free();
            queues.insertFeedback(currentProcess, 0);
            System.out.println(currentProcess.getPriority()+"");
            currentProcess = null;
            count = 0;
        }
    }
    
    public void print(){
        System.out.println("Disk Acess: ");
        if(currentProcess != null)
            currentProcess.print();
    }

    @Override
    public void draw(Graphics g) {

    }

    public void draw(Graphics g, int x) {
        g.setColor(Color.BLACK);
        g.drawString("Disk acess "+diskIndex, 250+x, 60*10);
        if(currentProcess != null)
            currentProcess.draw(g,250+x, 60*10);
    }

}
