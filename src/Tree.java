package src;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        bounds.x = GeneralConstants.treeXBounds;
        bounds.y = GeneralConstants.treeYBounds;
        bounds.width = GeneralConstants.treeWidth;
        bounds.height = GeneralConstants.treeHeight;
    }

    @Override
    public boolean isFirstRender() {
        return false;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
                (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
                bounds.width, bounds.height);
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }

    @Override
    public void die()
    {
        handler.getWorld().getEntityManager().removeEntity(this);
    }

    @Override
    public void interactedWith() {
        isInteracting = true;
        isInteracting = false;
    }

    @Override
    public boolean isInteracting() {
        return isInteracting;
    }
}
