package hansha.android.whackamole;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Set screen to this activity
        setContentView(R.layout.activity_main_menu);

        //If Its on Kit Kat or above go immersive mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int UI_OPTIONS = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
            getWindow().getDecorView().setSystemUiVisibility(UI_OPTIONS);
        }
    }

    public void playGame(View v) {
        Intent i = new Intent(getApplicationContext(), GameActivity.class);
        startActivityForResult(i,1);
    }

    @Override
    public void onActivityResult(int req, int res, Intent i) {
        ((TextView)findViewById(R.id.textView2)).setText("Latest Score: " + res);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        int x = (int) event.getX();  // or getRawX();
//        int y = (int) event.getY();
//        Log.d(TAG, "onTouchEvent: dsa");
//        switch (action) {
//            case MotionEvent.ACTION_BUTTON_PRESS:
//                ImageButton tiny = (ImageButton)findViewById(R.id.imageButton);
//                tiny.setBackgroundResource(R.drawable.play_button_pressed);
//                Log.d(TAG, "onTouchEvent: dsasdaas");
//                return true;
//            case MotionEvent.ACTION_BUTTON_RELEASE:
//                ImageButton t = (ImageButton)findViewById(R.id.imageButton);
//                t.setBackgroundResource(R.drawable.play_button);
//                return true;
//        }
//        return false;
//    }
}
