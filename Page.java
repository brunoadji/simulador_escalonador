package simulador_escalonador;

public class Page {
    private ProcessOS process;
    private Frame frame;

    public Page(ProcessOS process) {
        this.process = process;
        frame = null;
    }

    public ProcessOS getProcess() {
        return this.process;
    }

    public Frame getFrame() {
        return this.frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public void clean() {
        this.frame.freeFrame();
        this.process = null;
    }
}
