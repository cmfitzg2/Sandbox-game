package src;

public class GeneralUtils {

    public static Tile getTileAtPosition(float x, float y, Handler handler) {
        int tileX = Math.round(x / Tile.TILEWIDTH);
        int tileY = Math.round(y / Tile.TILEHEIGHT);
        return handler.getWorld().getTile(tileX, tileY);
    }
}
