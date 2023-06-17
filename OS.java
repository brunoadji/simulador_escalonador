package simulador_escalonador;

/*   Essa classe guarda a fila, os escalonadores e a CPU
 *   A ela pertence o loop, e ela vai chamar as funções dos outros módulos
 *   
 *   realiza a comunicação entre CPU, Filas e escalonadores 
 *   
 *   
 */

public class OS {
    LTScheduler ltScheduler;
    Dispatcher dispatcher;
    ProcessListGroup queues;
    MemAccessComp accessD0;
    MemAccessComp accessD1;
    MemAccessComp accessD2;
    MemAccessComp accessD3;
    CPU cpu;

    public OS(){
        queues = new ProcessListGroup();

        cpu = new CPU(queues);

        dispatcher = new Dispatcher(queues, cpu);

        ltScheduler = new LTScheduler(queues);

        accessD0 = new MemAccessComp(queues, 0);
        accessD1 = new MemAccessComp(queues, 1);
        accessD2 = new MemAccessComp(queues, 2);
        accessD3 = new MemAccessComp(queues, 3);
    }

    public void createProcess(String ID, int commingTime, int priority, int cpuTime, 
        int byteLength, int diskIndex, int IOstart, int IOend){

        ProcessOS p = new ProcessOS(ID, commingTime, priority, cpuTime, byteLength, diskIndex, IOstart, IOend);
        ltScheduler.accept(p);
    }

    public void executeCicle(){
        if(cpu.isFree())
            dispatcher.dispatch();

        cpu.runProcess();

        accessD0.access();
        accessD1.access();
        accessD2.access();
        accessD3.access();
    }

    public void print(){
        cpu.print();
        queues.print();
        accessD0.print();
        accessD1.print();
        accessD2.print();
        accessD3.print();
    }

}