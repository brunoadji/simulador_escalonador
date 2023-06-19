package simulador_escalonador;

public class Frame {
    private Page page;

    public Frame() {
        this.page = null;
    }

    public boolean setPage(Page newPage) {
        if(!this.havePage()) {
            this.page = newPage;
            return true;
        }
        return false;
    }

    public boolean havePage() {
        return (this.page != null);
    }

    public void freeFrame() {
        this.page = null;
    }
}
