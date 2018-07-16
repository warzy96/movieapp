package fiveagency.internship.food.movieapp.ui.utils;

import android.content.Context;
import android.support.v4.widget.CircularProgressDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

public final class ImageLoaderImpl implements ImageLoader {

    private final Context context;

    public ImageLoaderImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void renderImage(final String imageSource, final ImageView imageView, final float circularProgressbarStrokeWidth) {
        Glide.with(context)
             .load(imageSource)
             .apply(new RequestOptions().placeholder(initCircularProgressDrawable(circularProgressbarStrokeWidth)))
             .into(imageView);
    }

    private CircularProgressDrawable initCircularProgressDrawable(final float circularProgressbarStrokeWidth) {
        final CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(circularProgressbarStrokeWidth);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }
}
