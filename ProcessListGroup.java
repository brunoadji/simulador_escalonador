package simulador_escalonador;
import java.util.LinkedList;
import java.util.Queue;

/*   Essa classe possui as 8 filas de processos
 *   As filas de processo são divididas em: 3 de Feedback, 1 de FCFS e 4 de Bloqueados, 1 para cada disco.
 *
 *   Ela fornece métodos para inserir processos na fila e retirar processos da fila
 * 
 *   Atenção, é uma fila e não uma lista
 * 
 *   Tem que ter funções dessas para cada fila para os módulos externos poderem acessar todas elas
 *   Esses métodos funcionam como canais de comunicação para os outros módulos 
 * 
 *   ATENÇÃO: Não implementem nenhuma politica de escalonamento aqui
 *
 *   A única checagem a ser feita aqui é se um processo já pode retornar para pronto
 *
 * 
 */

public class ProcessListGroup {
    private int amountFeedbackQueues = 3;
    private int amountBlockQueues = 4;

    private Queue<ProcessOS> priorityQueue;

    private Queue<ProcessOS>[] feedbackQueue;
    private Queue<ProcessOS>[] blockQueue;
    
    public ProcessListGroup() {
        priorityQueue = new LinkedList<ProcessOS>();
        
        feedbackQueue = new LinkedList[amountFeedbackQueues];
        blockQueue = new LinkedList[amountBlockQueues];

        for(int i = 0; i < amountFeedbackQueues; i++) {
            feedbackQueue[i] = new LinkedList<ProcessOS>();
        }

        for(int i = 0; i < amountBlockQueues; i++) {
            blockQueue[i] = new LinkedList<ProcessOS>();
        }
    }

    //Inserindo na fila de prioridade 0
    void insertPriorityQueue(ProcessOS p) {
        priorityQueue.add(p);
    }

    //Inserindo na fila index de prioridade 1
    void insertFeedback(ProcessOS p, int index) {
        feedbackQueue[index].add(p);
    }

    //Inserindo na fila de bloqueados para o disco index
    void insertBlock(ProcessOS p, int index) {
        blockQueue[index].add(p);
    }

    //Remove e retorna da fila de prioridade 0
    ProcessOS getFromPriorityQueue() {
        return priorityQueue.poll();
    }

    //Remove e retorna da fila index de prioridade 1
    ProcessOS getFromFeedbackQueue(int index) {
        return feedbackQueue[index].poll();
    }

    //Remove e retorna da fila de bloqueado do disco index
    ProcessOS getFromBlockQueue(int index) {
        return blockQueue[index].poll();
    }

    public void print(){
        System.out.println("Priority queue: "+priorityQueue.size());

        for (ProcessOS process : priorityQueue) {
            process.print();
        }

        int i = 0;

        for (Queue<ProcessOS> queue : feedbackQueue) {
            System.out.println("feedback queue "+i+": ");
            i++;

            for (ProcessOS process : queue) {
                process.print();
            }
        }

        i = 0;
        
        for (Queue<ProcessOS> queue : blockQueue) {
            System.out.println("blocked queue "+i+": ");
            i++;
            for (ProcessOS process : queue) {
                process.print();
            }
        }
    }
}