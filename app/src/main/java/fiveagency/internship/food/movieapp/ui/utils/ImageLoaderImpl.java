package fiveagency.internship.food.movieapp.ui.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.Nullable;
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
             .addListener(new RequestListener<Drawable>() {

                 @Override
                 public boolean onLoadFailed(@Nullable final GlideException e, final Object model, final Target<Drawable> target, final boolean isFirstResource) {
                     target.onLoadFailed(context.getDrawable(R.drawable.ic_broken_image_black_24dp));
                     return true;
                 }

                 @Override
                 public boolean onResourceReady(final Drawable resource, final Object model, final Target<Drawable> target, final DataSource dataSource,
                                                final boolean isFirstResource) {
                     return false;
                 }
             })
             .apply(new RequestOptions().placeholder(initCircularProgressDrawable(getCircularProgressbarStrokeWidth(circularProgressbarStrokeWidth))))
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
