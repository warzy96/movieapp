package fiveagency.internship.food.movieapp.ui.movieslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fiveagency.internship.food.movieapp.MainActivity;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.app.MovieApplication;
import fiveagency.internship.food.movieapp.injection.ObjectGraph;

public final class MoviesListFragment extends Fragment implements MoviesListContract.View {

    public static final String TAG = "MoviesListFragment";
    private MoviesListContract.Presenter presenter;
    private MoviesListAdapter moviesListAdapter;
    private ObjectGraph objectGraph;

    public static MoviesListFragment newInstance() {
        return new MoviesListFragment();
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        objectGraph = MovieApplication.from(getContext()).getObjectGraph();
        presenter = objectGraph.provideMoviesListPresenter(this, objectGraph.provideRouter((MainActivity) getActivity()));
        moviesListAdapter = objectGraph.provideMoviesListAdapter(getContext());
        moviesListAdapter.setOnMovieClickListener(movieId -> presenter.showMovieDetails(movieId));
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView(view);
        presenter.start();
    }

    @Override
    public void render(final MoviesListViewModel moviesListViewModel) {
        moviesListAdapter.setMovies(moviesListViewModel.movieViewModelList);
    }

    private void initRecyclerView(final View rootView) {
        final RecyclerView recyclerView = rootView.findViewById(R.id.movies_list_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(moviesListAdapter);
    }
}
