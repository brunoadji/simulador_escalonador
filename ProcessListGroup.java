package simulador_escalonador;
import java.util.LinkedList;
import java.util.Queue;

/*   Essa classe possui as 5 filas de processos
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
    private Queue<Process> p0;
    private Queue<Process> q0;
    private Queue<Process> q1;
    private Queue<Process> q2;
    private Queue<Process> block;
    
    public ProcessListGroup() {
        p0 = new LinkedList();
        q0 = new LinkedList();
        q1 = new LinkedList();
        q2 = new LinkedList();
        block = new LinkedList();
    }

    //Inserindo na fila de prioridade 0
    void insertP0(Process p){
        p0.add(p);
    }

    //Filas de prioridade 1
    void insertQ0(Process p){
        q0.add(p);
    }

    void insertQ1(Process p){
        q1.add(p);
    }

    void insertQ2(Process p){
        q2.add(p);
    }

    Process getFromP0(){

    }

    Process getFromQ0(){
        
    }

    Process getFromQ1(){
        
    }

    Process getFromQ2(){
        
    }
}
