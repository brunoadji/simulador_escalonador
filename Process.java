package simulador_escalonador;

/*   Essa classe modela o processo
 *   Tem que ter uma função executar, que decrementa o numero de linhas restante
 */

public class Process {
    public static final int END = 0;
    public static final int READY = 1;
    public static final int BLOCK = 2;

    private int state = Process.READY;

    private int commingTime;
    private int priority;
    private int cpuTime;
    private int byteLength;
    private int diskIndex;
    private int IOstart;
    private int IOend;

    private int queueIndex = 0;

    private int executedTime;

    public Process(
        int commingTime, int priority, int cpuTime, 
        int byteLength, int diskIndex, int IOstart, int IOend)
    {
        this.commingTime = commingTime;
        this.priority = priority;
        this.cpuTime = cpuTime;
        this.byteLength = byteLength;
        this.diskIndex = diskIndex;
        this.IOstart = IOstart;
        this.IOend = IOend;
    }

    public int getPriority() {
        return priority;
    }

    public int getDiskIndex() {
        return diskIndex;
    }

    public int getQueueIndex() {
        return queueIndex;
    }

    public void free(){
        state = Process.READY;
    }

    public void incrementQueueIndex(){
        queueIndex++;
        queueIndex%=3;
    }

    // retorna end, block ou ready
    public int execute(){
        if(state == Process.BLOCK)
            return state;

        executedTime++;

        if(cpuTime-executedTime <= 0)
            state = Process.END;
        else if(commingTime+executedTime == IOstart)
            state = Process.BLOCK;

        return state;
    }
}
