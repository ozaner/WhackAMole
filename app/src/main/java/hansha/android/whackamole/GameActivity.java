package hansha.android.whackamole;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    GameView gameView;
    public static int w,h;
    public static MediaPlayer mp1,mp2,mp3,mp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Set screen to this activity
        gameView = new GameView(this, this);
        setContentView(gameView);
        //setContentView(new GamePanel(this));

        //If Its on Kit Kat or above go immersive mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        w = metrics.widthPixels;
        h = metrics.heightPixels;
        mp1 = MediaPlayer.create(GameActivity.this, R.raw.bonk4);
        mp2 = MediaPlayer.create(GameActivity.this, R.raw.bonk2);
        mp3 = MediaPlayer.create(GameActivity.this, R.raw.bonk3);
        mp4 = MediaPlayer.create(GameActivity.this, R.raw.bonk1);
    }
}
