package com.example.pianotiles.page;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pianotiles.R;
import com.example.pianotiles.model.Tile;

import java.io.IOException;
import java.util.Random;

public class GamePage extends AppCompatActivity {
    Random random = new Random();
    private int score;
    private static MediaPlayer mediaPlayer;
    private boolean isPlaying;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);
        score = 0;
        // Get Container for tiles
        LinearLayout linearLayout = findViewById(R.id.linearLayout1);
        // Set media player to default audio file
        mediaPlayer = MediaPlayer.create(this, R.raw.twinkle_twinkle_little_star);
        // get duration in seconds
        int duration = (mediaPlayer.getDuration() / 1000) + 1;
        handler = new Handler();
        startTile(linearLayout, duration);
    }

    private void startTile(LinearLayout linearLayout, int duration) {
        int tilePosition = random.nextInt(5) + 1;
        Tile tile = new Tile(this, linearLayout, tilePosition);
        onClickTiles(linearLayout, tile.imageView);

        // Animate Tile
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        tile.animate(linearLayout, screenHeight);
        // Schedule next draw
        if (duration > 0) {
            int finalDuration = duration-1;
            int animationDelay = 1000;
            handler.postDelayed(() -> startTile(linearLayout, finalDuration), animationDelay);
        }
    }

    @SuppressLint("DefaultLocale")
    private void onClickTiles(LinearLayout linearLayout, ImageView imageView) {
        imageView.setOnClickListener(v -> {
            if (isPlaying) {
                // If audio is playing, pause it
                pauseAudio();
            } else {
                // If audio is not playing, play it for 2 seconds
                playAudio();
                // Set a flag indicating that audio is playing
                isPlaying = true;
                // Schedule to pause the audio after 2 seconds
                imageView.postDelayed(this::pauseAudio, 3000);
            }

            // Remove the ImageView
            imageView.clearAnimation();
            linearLayout.removeView(imageView);
            score++;
            TextView tv_score = findViewById(R.id.tv_score);
            tv_score.setText(String.format("SCORE: %d", score));
        });
    }

    private void pauseAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            // Set a flag indicating that audio is paused
            isPlaying = false;
        }
    }

    private void playAudio() {
        if (HomePage.audioUri == null) {
//            Toast.makeText(this, "Please select an audio file first", Toast.LENGTH_SHORT).show();
            playDefaultSound();
            return;
        }

        // Initialize MediaPlayer
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        try {
            // Set data source to the selected audio file
            mediaPlayer.setDataSource(this, HomePage.audioUri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show();
        }
    }

    private void playDefaultSound() {
        // Initialize MediaPlayer
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.twinkle_twinkle_little_star);
        }

        // Check if the MediaPlayer is playing, stop it, and then start again
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, R.raw.twinkle_twinkle_little_star);
        }

        // Start playing the sound
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Release MediaPlayer resources when the activity is destroyed
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}
