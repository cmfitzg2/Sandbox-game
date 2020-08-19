package src;

import java.awt.*;
import java.util.ArrayList;

public class ItemManager {

    private Handler handler;
    private Player player;
    private ArrayList<Item> items;
    private Item item;

    public ItemManager(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
        items = new ArrayList<>();
    }

    public void tick()
    {
        for (Item item : items) {
            item.tick();
        }
    }

    public void render(Graphics g)
    {
        for (Item item : items) {
            item.render(g);
        }
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
