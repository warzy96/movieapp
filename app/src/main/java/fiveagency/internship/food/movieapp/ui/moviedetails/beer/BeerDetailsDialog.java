package fiveagency.internship.food.movieapp.ui.moviedetails.beer;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;

public final class BeerDetailsDialog extends Dialog {

    @BindView(R.id.beer_title_text_view)
    TextView titleTextView;

    @BindView(R.id.beer_image_view)
    ImageView beerImageView;

    @BindView(R.id.first_brewed_text_view)
    TextView firstBrewedTextView;

    @BindView(R.id.description_text_view)
    TextView descriptionTextView;

    @BindView(R.id.abv_text_view)
    TextView abvTextView;

    @BindView(R.id.food_pairings_linear_layout)
    LinearLayout foodPairingsLinearLayout;

    @BindDimen(R.dimen.circular_progressbar_stroke_width)
    float circularProgressbarStrokeWidth;

    ImageLoader imageLoader;

    BeerViewModel beerViewModel;

    public BeerDetailsDialog(@NonNull Context context) { super(context); }

    public BeerDetailsDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected BeerDetailsDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show() {
        setContentView(R.layout.beer_details_dialog_layout);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(layoutParams);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ButterKnife.bind(this);
        titleTextView.setText(beerViewModel.getName());
        firstBrewedTextView.setText(beerViewModel.getFirstBrewed());
        descriptionTextView.setText(beerViewModel.getDescription());
        abvTextView.setText(String.valueOf(beerViewModel.getAlcoholByVolume()));
        imageLoader.renderImage(beerViewModel.getImageUrl(), beerImageView, circularProgressbarStrokeWidth);
        for (String foodPairing : beerViewModel.getFoodPairing()) {
            TextView textView = new TextView(getContext());
            textView.setText(foodPairing);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getContext().getResources().getDimension(R.dimen.default_text_size));
            textView.setTextColor(getContext().getResources().getColor(R.color.text_dark));
            foodPairingsLinearLayout.addView(textView);
        }

        super.show();
    }

    /**
     * Call this method before calling show.
     *
     * @param beerViewModel - data to be shown
     */
    public void setBeerViewModel(final BeerViewModel beerViewModel, final ImageLoader imageLoader) {
        this.beerViewModel = beerViewModel;
        this.imageLoader = imageLoader;
    }
}
