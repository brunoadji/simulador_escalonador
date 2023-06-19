package simulador_escalonador;

public class Memory {
    private int capacity;
    private int frameSize;
    private int framesAmount;
    private Frame[] frames;

    public Memory(int capacity) {
        this.capacity = capacity * 1024;
        this.frameSize = 256;
        this.framesAmount = capacity * 1024 / this.frameSize; //receive the capacity in GB and each frame will have 256MB
        this.frames = new Frame[framesAmount];

        for(int i = 0; i < framesAmount; i++) {
            this.frames[i] = new Frame();
        }
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int freeSpace() {
        return this.countFreeFrames() * this.frameSize;
    }

    public boolean allocProcess(ProcessOS process) {
        Page[] pages = process.getPages(this.frameSize);
        
        int amountNeeded = process.getAmountPages(frameSize);
        int freeFrames = countFreeFrames();
        if(amountNeeded > freeFrames) return false;

        int index = 0;

        for(int i = 0; i < this.framesAmount; i++) {
            if(this.frames[i].setPage(pages[index])) {
                index++;
                if(index == amountNeeded) break;
            }
        }

        return true;
    }

    private int countFreeFrames() {
        int amount = 0;
        for(int i = 0; i < this.framesAmount; i++) {
            if(!this.frames[i].havePage()) amount++;
        }
        return amount;
    }
}
