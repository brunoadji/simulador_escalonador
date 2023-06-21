package simulador_escalonador;

/* Escalonador de curto prazo, quando chamado vai tirar um processo das listas de processo
 *  E o despachar para a CPU
 * 
 * 
 */

public class Dispatcher {

    private ProcessListGroup queue;
    
    public Dispatcher(ProcessListGroup queue, CPU[] cpu){
        this.queue = queue;
    }

    public void dispatch(CPU cpu){
        ProcessOS p;

        p = queue.getFromPriorityQueue();
        
        for(int i = 0; i < 3 && p == null; i++) {
            p = queue.getFromFeedbackQueue(i);
        }

        cpu.setProcess(p);
    }
}
