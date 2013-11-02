package com.noworknoplay.crystalball;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;

public class MainActivity extends Activity {

    private CrystallBall mCrystalBall = new CrystallBall();
    private TextView mAnswerLabel;
    private Button mGetAnswerButton;
    private ImageView mCrystalBallImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assign the views from the layout file
        mAnswerLabel = (TextView) findViewById(R.id.textView);
        mGetAnswerButton = (Button) findViewById(R.id.button);
        mCrystalBallImage = (ImageView) findViewById(R.id.imageView);

        mGetAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String answer = mCrystalBall.getAnAnswer();

                // Update the label with our dynamic answer
                mAnswerLabel.setText(answer);

                animateCrystalBall();
                animateAnswer();
                playSound();
            }
        });

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    private void animateCrystalBall() {
        mCrystalBallImage.setImageResource(R.drawable.ball_animation);

        AnimationDrawable ballAnimation = (AnimationDrawable) mCrystalBallImage.getDrawable();
        if (ballAnimation.isRunning()) {
            ballAnimation.stop();
        }
        ballAnimation.start();
    }

    private void animateAnswer() {
        AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
        fadeInAnimation.setDuration(1500);
        fadeInAnimation.setFillAfter(true);

        mAnswerLabel.setAnimation(fadeInAnimation);
    }

    private void playSound() {
        MediaPlayer player = MediaPlayer.create(this, R.raw.crystal_ball);
        player.start();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}