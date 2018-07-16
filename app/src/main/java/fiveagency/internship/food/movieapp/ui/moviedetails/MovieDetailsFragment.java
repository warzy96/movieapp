package fiveagency.internship.food.movieapp.ui.moviedetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.movieapp.MainActivity;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.ObjectGraph;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;

public final class MovieDetailsFragment extends Fragment implements MovieDetailsContract.View {

    public static final String TAG = "MovieDetailsFragment";
    private static final String KEY_MOVIE_ID = "key_movie_id";
    @BindView(R.id.movie_details_movie_name)
    TextView movieDetailsMovieName;
    @BindView(R.id.movie_details_overview_text_view)
    TextView movieDetailsMovieOverview;
    @BindView(R.id.movie_details_image_view)
    ImageView movieDetailsPoster;
    @BindDimen(R.dimen.circular_progressbar_stroke_width)
    float circularProgressbarStrokeWidth;
    //@Inject
    private ImageLoader imageLoader;
    private MovieDetailsContract.Presenter presenter;
    private ObjectGraph objectGraph;

    public static MovieDetailsFragment newInstance(final int movieId) {
        final Bundle arguments = new Bundle();
        arguments.putInt(KEY_MOVIE_ID, movieId);
        final MovieDetailsFragment movieDetailsFragment = new MovieDetailsFragment();
        movieDetailsFragment.setArguments(arguments);
        return movieDetailsFragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objectGraph = MovieApplication.from(getContext()).getObjectGraph();
        imageLoader = objectGraph.provideImageLoader();
        presenter = objectGraph.provideMovieDetailsPresenter(this, objectGraph.provideRouter((MainActivity) getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_movie_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle argumentsBundle = getArguments();
        if (argumentsBundle != null) {
            if (argumentsBundle.containsKey(KEY_MOVIE_ID)) {
                presenter.start(argumentsBundle.getInt(KEY_MOVIE_ID));
            } else {
                throw new IllegalArgumentException("Arguments bundle does not contain \"" + KEY_MOVIE_ID + "\" key.");
            }
        } else {
            throw new IllegalArgumentException("Arguments bundle does not exist.");
        }
    }

    @Override
    public void render(final MovieDetailsViewModel movieDetailsViewModel) {
        movieDetailsMovieName.setText(movieDetailsViewModel.title);
        movieDetailsMovieOverview.setText(movieDetailsViewModel.overview);
        imageLoader.renderImage(movieDetailsViewModel.imageSource, movieDetailsPoster, circularProgressbarStrokeWidth);
    }
}
