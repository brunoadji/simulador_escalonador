package simulador_escalonador;

import java.awt.Graphics;

/*   Essa classe guarda a fila, os escalonadores e a CPU
 *   A ela pertence o loop, e ela vai chamar as funções dos outros módulos
 *   
 *   realiza a comunicação entre CPU, Filas e escalonadores 
 *   
 *   
 */

public class OS implements Drawnable{
    LTScheduler ltScheduler;
    Dispatcher dispatcher;
    ProcessListGroup queues;
    MemAccessComp accessD0;
    MemAccessComp accessD1;
    MemAccessComp accessD2;
    MemAccessComp accessD3;
    CPU[] cpu;

    Memory memory;

    public OS(){
        queues = new ProcessListGroup();

        cpu = new CPU[4];
        for(int i = 0; i < 4; i++) {
            cpu[i] = new CPU(queues);
        }

        dispatcher = new Dispatcher(queues, cpu);

        ltScheduler = new LTScheduler(queues);

        accessD0 = new MemAccessComp(queues, 0);
        accessD1 = new MemAccessComp(queues, 1);
        accessD2 = new MemAccessComp(queues, 2);
        accessD3 = new MemAccessComp(queues, 3);

        memory = new Memory(32);
    }

    public void createProcess(String ID, int commingTime, int priority, int cpuTime, 
        int byteLength, int diskIndex, int IOstart, int IOend){

        if(memory.freeSpace()<byteLength) return;

        ProcessOS p = new ProcessOS(ID, commingTime, priority, cpuTime, byteLength, diskIndex, IOstart, IOend);
        
        memory.allocProcess(p);
        ltScheduler.accept(p);
    }

    public void executeCicle(){
        for(int i = 0; i < 4; i++) {
            if(cpu[i].isFree())
                dispatcher.dispatch(cpu[i]);

            cpu[i].runProcess();
        }

        accessD0.getProcess();
        accessD1.getProcess();
        accessD2.getProcess();
        accessD3.getProcess();

        accessD0.access();
        accessD1.access();
        accessD2.access();
        accessD3.access();
    }

    public void print(){
        for(int i = 0; i < 4; i++) {
            cpu[i].print();
        }
        queues.print();
        accessD0.print();
        accessD1.print();
        accessD2.print();
        accessD3.print();
    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < 4; i++) {
            cpu[i].draw(g, i*60, i);
        }
        queues.draw(g);
        accessD0.draw(g, 100);
        accessD1.draw(g, 200);
        accessD2.draw(g, 300);
        accessD3.draw(g, 400);
        memory.draw(g);
    }

}