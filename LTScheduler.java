package simulador_escalonador;

//Escalonador de longo prazo precisa acessar as filas e colocar um processo novo na fila de prontos
// O SO vai acessá-lo, passar o novo processo como parametro e ele então pode o escalonar

public class LTScheduler {
    
    private ProcessListGroup queue;

    public LTScheduler(ProcessListGroup q){
        queue = q;
    }

    public void accept(Process p){
        if(p.getPriority() == 0)
            queue.insertPriorityQueue(p);
        else
            queue.insertFeedback(p, 0);
    }
}