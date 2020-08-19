package src;

import java.awt.*;

public abstract class Item {

    private Handler handler;
    private int id;
    public Item(Handler handler, int id) {
        this.handler = handler;
        this.id = id;
    }


    public abstract void tick();

    public abstract void render(Graphics g);
}
