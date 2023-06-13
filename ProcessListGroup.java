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

    private Queue<Process> priorityQueue;

    private Queue<Process>[] feedbackQueue;
    private Queue<Process>[] blockQueue;
    
    public ProcessListGroup() {
        priorityQueue = new LinkedList<Process>();

        feedbackQueue = new LinkedList[amountFeedbackQueues];
        blockQueue = new LinkedList[amountBlockQueues];

        for(int i = 0; i < amountFeedbackQueues; i++) {
            feedbackQueue[i] = new LinkedList<Process>();
        }

        for(int i = 0; i < amountBlockQueues; i++) {
            blockQueue[i] = new LinkedList<Process>();
        }
    }

    //Inserindo na fila de prioridade 0
    void insertPriorityQueue(Process p) {
        priorityQueue.add(p);
    }

    void insertFeedback(Process p, int index) {
        feedbackQueue[index].add(p);
    }

    void insertBlock(Process p, int index) {
        blockQueue[index].add(p);
    }

    Process getFromPriorityQueue() {
        return priorityQueue.remove();
    }

    Process getFromFeedbackQueue(int index) {
        return feedbackQueue[index].remove();
    }

    Process getFromBlockQueue(int index) {
        return blockQueue[index].remove();
    }
}