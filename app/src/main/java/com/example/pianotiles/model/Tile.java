package com.example.pianotiles.model;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Tile {

    public ImageView imageView;
    private final int height = 400;
    private final int width = 216;
    private final int speed = 5000;

    public Tile(Context context, LinearLayout linearLayout, int tileNumber) {
        // Create the ImageView programmatically
        imageView = new ImageView(context); // Pass the context
        imageView.setId(tileNumber);
        // Set the layout properties dynamically
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                width, // Width in pixels
                height // Height in pixels
        ));
        imageView.setX(getTilePosition(tileNumber));
        imageView.setBackgroundColor(Color.BLACK); // Black background
        int tileMargin = 2;
        imageView.setPadding(tileMargin, tileMargin, tileMargin, tileMargin); // Margin of 2dp around the image
        linearLayout.addView(imageView);
    }

    private int getTilePosition(int tileNumber) {
        return width * (tileNumber-1);
    }

    public void animate(LinearLayout linearLayout, int screenHeight) {
        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setDuration(speed); // Adjust duration as needed (in milliseconds)

        // Translate up off-screen
        animationSet.playSequentially(
                ObjectAnimator.ofFloat(imageView, "translationY", -height, screenHeight)
        );

        animationSet.start();
    }
}
