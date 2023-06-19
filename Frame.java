package simulador_escalonador;

import java.awt.Color;
import java.awt.Graphics;

public class Frame implements Drawnable{
    private Page page;

    public Frame() {
        this.page = null;
    }

    public boolean setPage(Page newPage) {
        if(!this.havePage()) {
            this.page = newPage;
            newPage.setFrame(this);
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

    @Override
    public void draw(Graphics g) {}

    public void draw(Graphics g, int i) {
        g.setColor(Color.white);
        g.fill3DRect(915, 30+50*i,270, 40, false);

        
        g.setColor(Color.black);

        if(page == null){
            g.drawString("null",910+20, 30+50*i+20);
        }else{
            page.draw(g, 915+20, 30+50*i+20);
        }
    }
}
