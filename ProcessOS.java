package simulador_escalonador;

/*   Essa classe modela o processo
 *   Tem que ter uma função executar, que decrementa o numero de linhas restante
 */

public class ProcessOS {
    public static final int END = 0;
    public static final int READY = 1;
    public static final int BLOCK = 2;

    private int state = ProcessOS.READY;

    private String ID;
    private int commingTime;
    private int priority;
    private int cpuTime;
    private int byteLength;
    private int diskIndex;
    private int IOstart;
    private int IOend;

    private int queueIndex = 0;

    private int executedTime;

    public ProcessOS(
        String ID, int commingTime, int priority, int cpuTime, 
        int byteLength, int diskIndex, int IOstart, int IOend)
    {
        this.ID = ID;
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

    public int getIOend() {
        return IOend;
    }

    public void free(){
        state = ProcessOS.READY;
    }

    public void incrementQueueIndex(){
        queueIndex++;
        queueIndex%=3;
    }

    // retorna end, block ou ready
    public int execute(){
        if(state == ProcessOS.BLOCK)
            return state;

        executedTime++;

        if(cpuTime-executedTime <= 0)
            state = ProcessOS.END;
        else if(commingTime+executedTime == IOstart)
            state = ProcessOS.BLOCK;

        return state;
    }

    public void print(){
        System.out.println(ID);
    }
}
