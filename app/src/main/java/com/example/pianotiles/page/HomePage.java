package com.example.pianotiles.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pianotiles.R;

public class HomePage extends AppCompatActivity {
    public static Uri audioUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Button buttonSelectAudio = findViewById(R.id.buttonSelectAudio);
        buttonSelectAudio.setOnClickListener(this::chooseAudio);

        Button buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this::onClickStart);
    }

    public void chooseAudio(View view) {
        // Set up the launcher for choosing audio file
        ActivityResultLauncher<String> audioPickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                result -> {
                    if (result != null) {
                        audioUri = (Uri) result;
                    }
                });
        // Launch the audio picker activity
        audioPickerLauncher.launch("audio/*");

        // Create an intent to pick an audio file
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(Intent.createChooser(intent, "Select Audio File"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check if the request was successful and audio file was selected
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            // Get the URI of the selected audio file
            audioUri = data.getData();
        }
    }

    private void onClickStart(View e) {
        Intent intent = new Intent(HomePage.this, GamePage.class);
        startActivity(intent);
    }

}
