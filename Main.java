package simulador_escalonador;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    
    public static void main(String[] args) {
        OS os = new OS();

        os.createProcess("P1",0, 0, 10, 2504, 0, 4, 6);

        os.executeCicle();
        /*
        Queue<ProcessOS> priorityQueue = new LinkedList<ProcessOS>();
        priorityQueue.add(new ProcessOS("P1",0, 0, 10, 2504, 0, 4, 6));

        for (ProcessOS processOS : priorityQueue) {
                processOS.print();;
        }

        for (ProcessOS processOS : priorityQueue) {
                processOS.print();;
        }*/

        os.print();
    }

}
