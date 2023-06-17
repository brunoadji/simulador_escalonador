package simulador_escalonador;

/* Escalonador de curto prazo, quando chamado vai tirar um processo das listas de processo
 *  E o despachar para a CPU
 * 
 * 
 */

public class Dispatcher {

    private ProcessListGroup queue;
    private CPU cpu;
    
    public Dispatcher(ProcessListGroup queue, CPU cpu){
        this.queue = queue;
        this.cpu = cpu;
    }

    public void dispatch(){
        ProcessOS p;

        p = queue.getFromPriorityQueue();

        int i=-1;

        while(p == null && i<3){
            i++;
            p = queue.getFromFeedbackQueue(i);
        }

        cpu.setProcess(p);
    }
}
