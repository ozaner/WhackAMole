package hansha.android.whackamole;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static hansha.android.whackamole.GameActivity.mp1;
import static hansha.android.whackamole.GameActivity.mp2;
import static hansha.android.whackamole.GameActivity.mp3;
import static hansha.android.whackamole.GameActivity.mp4;

/**
 * Game View
 */
public class GameView extends SurfaceView {

    public static final int FPS = 30;

    public static Bitmap bg, top, middle, bottom, heart;
    public static int screen_width, screen_height;
    private static Paint p;
    private Mole mole = new Mole();
    private int score = 0;
    private int lives = 3;
    private int time = 30;
    private int timeCounter = 0;
    private Activity a;

    public GameView(Context context, Activity a) {
        super (context);
        p = new Paint();
        this.a = a;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager()
                .getDefaultDisplay()
                .getRealMetrics(displayMetrics);

        screen_width = displayMetrics.widthPixels;
        screen_height = displayMetrics.heightPixels;

        Mole.molePNG = getScaledPNG(R.drawable.mole,.20,.13);
        bg = getScaledPNG(R.drawable.bg);
        top = getScaledPNG(R.drawable.bg_top);
        middle = getScaledPNG(R.drawable.bg_middle);
        bottom = getScaledPNG(R.drawable.bg_bottom);
        heart = getScaledPNG(R.drawable.heart,.1,.05);
        setWillNotDraw(false); //Why is this default. Come on Google!

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(mole.getAnimationFrame() < 10)
                    mole.setAnimationFrame(mole.getAnimationFrame()+1);
                timeCounter++;
                if(timeCounter >= FPS) {
                    time--;
                    timeCounter = 0;
                }
                postInvalidate();
            }
        }, 0, 1000/FPS);
    }

    public Bitmap getScaledPNG(int id) {
        return getScaledPNG(id,1,1);
    }

    public Bitmap getScaledPNG(int id, double widthScale, double heightScale) {
        Bitmap png = BitmapFactory.decodeResource(getResources(), id);
        return Bitmap.createScaledBitmap(png, (int)(screen_width*widthScale),
                (int)(screen_height*heightScale), true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(lives == 0 || time == 0) {
            p.setTextSize(80);
            canvas.drawText("Score: " + score,500,600,p);
            a.setResult(score);
            a.finish();
        }
        canvas.drawBitmap(bg,0,0,p);
        if(mole.getHoleY() <= 0)
            mole.drawMole(canvas,p);
        canvas.drawBitmap(top,0,0,p);
        if(mole.getHoleY() == 1)
            mole.drawMole(canvas,p);
        canvas.drawBitmap(middle,0,0,p);
        if(mole.getHoleY() >= 2)
            mole.drawMole(canvas,p);
        canvas.drawBitmap(bottom,0,0,p);
        if(lives >= 1)
            canvas.drawBitmap(heart,950,30,p);
        if(lives >= 2)
            canvas.drawBitmap(heart,900,30,p);
        if(lives >= 3)
            canvas.drawBitmap(heart,850,30,p);

        p.setTextSize(80);
        canvas.drawText("Score: " + score,50,100,p);
        canvas.drawText("Time: " + time,50,200,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();  // or getRawX();
        int y = (int) event.getY();

        int moleX = mole.getMoleCoords().x;
        int moleY = mole.getMoleCoords().y;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (x >= moleX && x < (moleX + mole.getBitmap().getWidth())
                        && y >= moleY && y < (moleY + mole.getBitmap().getHeight())) {
                    mole.setHole(new Random().nextInt(3), new Random().nextInt(3));
                    score++;
                    mole.setAnimationFrame(0);
                    switch (new Random().nextInt(4)) {
                        case 1:
                            mp1.start();
                            break;
                        case 2:
                            mp2.start();
                            break;
                        case 3:
                            mp3.start();
                            break;
                        case 0:
                            mp4.start();
                            break;
                    }
                }
                else
                    lives--;
                postInvalidate();
                return true;

        }
        return false;
    }
}
