package fiveagency.internship.food.movieapp.ui.moviedetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fiveagency.internship.food.movieapp.MainActivity;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.ObjectGraph;

public final class MovieDetailsFragment extends Fragment implements MovieDetailsContract.View {

    public static final String TAG = "MovieDetailsFragment";
    private static final String KEY_MOVIE_ID = "key_movie_id";
    private MovieDetailsContract.Presenter presenter;
    private ObjectGraph objectGraph;

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
        objectGraph = MovieApplication.from(getContext()).getObjectGraph();
        presenter = objectGraph.provideMovieDetailsPresenter(this, objectGraph.provideRouter((MainActivity) getActivity()));
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
        final TextView textView = getView().findViewById(R.id.movie_details_movie_name);
        textView.setText(movieDetailsViewModel.title);
    }
}
