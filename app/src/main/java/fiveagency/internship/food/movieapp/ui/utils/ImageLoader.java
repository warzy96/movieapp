package fiveagency.internship.food.movieapp.ui.utils;

import android.view.View;
import android.widget.ImageView;

public interface ImageLoader {

    void renderImage(View parentView, String imageSource, ImageView imageView, float circularProgressbarStrokeWidth);
}
