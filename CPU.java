package simulador_escalonador;

/*  CPU vai chamar a executar de um processo,
 *  Checar o fim do processo ou instruções de I/O e levar um processo ao bloqueio
 *  Também colocará o processo na devida fila quando este perder CPU
 *  
 *   **É importante lembrar que a função de executar da CPU vai estar debaixo de um loop maior que a classe
 *   CPU, portanto não implementem essa função como um loop que executa, mas sim uma função que vai executar
 *   Uma linha a cada unidade de tempo. Esse loop das unidades de tempo vai ser controlado pela classe OS
*/  

public class CPU {

    private Process process = null;

    private ProcessListGroup queueManager;

    private int remainingQuantum = 2;

    public CPU(ProcessListGroup group){
        queueManager = group;
    }

    public boolean isFree(){
        return (process==null);
    }
    
    public void setProcess(Process process) {
        this.process = process;
        remainingQuantum = 2;
    }
    
    public void runProcess(Process p){
        if(process == null) return;

        int state = process.execute();

        remainingQuantum--;

        if(state == Process.END){
            process = null;
            return;
        }
        else if(state == Process.BLOCK){
            //insertBlock
        }else if(remainingQuantum==0){
            reinsert();
        }
    }

    public void reinsert(){
        if(process.getPriority()==0){
            queueManager.insertPriorityQueue(process);
        }else{
            queueManager.insertFeedback(process, process.getQueueIndex());
        }

        process = null;
    }
}
