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
    ProcessListGroup list;
    CPU cpu;

    public OS(){
        list = new ProcessListGroup();

        cpu = new CPU(list);

        dispatcher = new Dispatcher(list, cpu);

        ltScheduler = new LTScheduler(list);
    }

    public void createProcess(int commingTime, int priority, int cpuTime, 
        int byteLength, int diskIndex, int IOstart, int IOend){

        Process p = new Process(commingTime, priority, cpuTime, byteLength, diskIndex, IOstart, IOend);

        ltScheduler.accept(p);
    }

    public void executeCicle(){
        if(cpu.isFree())
            dispatcher.dispatch();
    }

}