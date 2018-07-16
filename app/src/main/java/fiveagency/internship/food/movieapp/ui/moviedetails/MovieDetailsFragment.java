package fiveagency.internship.food.movieapp.ui.moviedetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.CircularProgressDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;

public final class MovieDetailsFragment extends DaggerFragment implements MovieDetailsContract.View {

    public static final String TAG = "MovieDetailsFragment";
    private static final String KEY_MOVIE_ID = "key_movie_id";
    private static final int CIRCULAR_PROGRESS_DRAWABLE_STROKE_WIDTH = R.dimen.circular_progressbar_stroke_width;
    private static final int MOVIE_NAME_TEXT_VIEW = R.id.movie_details_movie_name;
    private static final int MOVIE_OVERVIEW_TEXT_VIEW = R.id.movie_details_overview_text_view;
    private static final int MOVIE_POSTER_IMAGE_VIEW = R.id.movie_details_image_view;

    @Inject
    MovieDetailsContract.Presenter presenter;

    public static MovieDetailsFragment newInstance(final int movieId) {
        final Bundle arguments = new Bundle();
        arguments.putInt(KEY_MOVIE_ID, movieId);
        MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(arguments);
        return movieDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.start(getArguments().getInt(KEY_MOVIE_ID));
    }

    @Override
    public void render(final MovieDetailsViewModel movieDetailsViewModel) {
        final TextView textView = getView().findViewById(MOVIE_NAME_TEXT_VIEW);
        textView.setText(movieDetailsViewModel.title);
        final TextView overviewTextView = getView().findViewById(MOVIE_OVERVIEW_TEXT_VIEW);
        overviewTextView.setText(movieDetailsViewModel.overview);
        final ImageView imageView = getView().findViewById(MOVIE_POSTER_IMAGE_VIEW);
        final CircularProgressDrawable circularProgressDrawable = initCircularProgressDrawable();
        Glide.with(getContext())
             .load(movieDetailsViewModel.imageSource)
             .apply(new RequestOptions().placeholder(circularProgressDrawable))
             .into(imageView);
    }

    private CircularProgressDrawable initCircularProgressDrawable() {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(getContext());
        circularProgressDrawable.setStrokeWidth(getResources().getDimension(CIRCULAR_PROGRESS_DRAWABLE_STROKE_WIDTH));
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
