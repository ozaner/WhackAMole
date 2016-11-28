package hansha.android.whackamole;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import static android.content.ContentValues.TAG;

/**
 * Mole Class
 */
public class Mole {

    public static final Point TOP_LEFT = new Point(130,970);
    public static Bitmap molePNG;

    private int animationFrame;
    private int xHole, yHole;

    public int getHoleX() {
        return xHole;
    }

    public int getHoleY() {
        return yHole;
    }

    public void setHole(int x, int y) {
        xHole = x;
        yHole = y;
    }

    public int getAnimationFrame() {
        return animationFrame;
    }

    public void setAnimationFrame(int x) {
        animationFrame = x;
    }

    public Point getMoleCoords() {
        return new Point(TOP_LEFT.x+xHole*330,TOP_LEFT.y+yHole*340-animationFrame*13);
    }

    public Bitmap getBitmap() {
        return molePNG;
    }

    public void drawMole(Canvas canvas, Paint paint) {
        canvas.drawBitmap(molePNG, getMoleCoords().x, getMoleCoords().y, paint);
    }
}
