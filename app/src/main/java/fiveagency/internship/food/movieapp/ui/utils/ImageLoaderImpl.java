package fiveagency.internship.food.movieapp.ui.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import fiveagency.internship.food.movieapp.R;

public final class ImageLoaderImpl implements ImageLoader {

    private final Context context;
    private final float defaultCircularProgressbarStrokeWidth;

    public ImageLoaderImpl(final Context context) {
        this.context = context;

        defaultCircularProgressbarStrokeWidth = context.getResources().getDimensionPixelSize(R.dimen.circular_progressbar_stroke_width);
    }

    @Override
    public void renderImage(final String imageSource, final ImageView imageView, final float circularProgressbarStrokeWidth) {
        Glide.with(context)
             .load(imageSource)
             .apply(new RequestOptions().placeholder(
                     initCircularProgressDrawable(getCircularProgressbarStrokeWidth(circularProgressbarStrokeWidth))))
             .into(imageView);
    }

    private float getCircularProgressbarStrokeWidth(final float circularProgressbarStrokeWidth) {
        return circularProgressbarStrokeWidth == 0f ? defaultCircularProgressbarStrokeWidth : circularProgressbarStrokeWidth;
    }

    private CircularProgressDrawable initCircularProgressDrawable(final float circularProgressbarStrokeWidth) {
        final CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(circularProgressbarStrokeWidth);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }
}
