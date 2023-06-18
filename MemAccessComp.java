package simulador_escalonador;

public class MemAccessComp {
    
    private ProcessListGroup queues;
    private int diskIndex;

    private ProcessOS currentProcess;

    private int count = 0;

    public MemAccessComp(ProcessListGroup queues, int diskIndex){
        this.queues = queues;
        this.diskIndex = diskIndex;
    }

    public void getProcess(){
        if(currentProcess == null)
            currentProcess = queues.getFromBlockQueue(diskIndex);
    }

    public void access(){
        if(currentProcess == null) return;

        count++;

        if(currentProcess.getIOend() <= count){
            queues.insertFeedback(currentProcess, 0);

            currentProcess = null;
        }
    }
    
    public void print(){
        System.out.println("Disk Acess: ");
        if(currentProcess != null)
            currentProcess.print();
    }
}
