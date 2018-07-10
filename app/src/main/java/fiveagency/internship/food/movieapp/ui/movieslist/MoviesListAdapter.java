package fiveagency.internship.food.movieapp.ui.movieslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fiveagency.internship.food.movieapp.R;

public final class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<MovieViewModel> movies = new ArrayList<>();
    private static final int ITEM_MOVIE_LAYOUT = R.layout.item_movie;
    private static final int MOVIE_NAME_TEXT_VIEW = R.id.movie_name;

    public MoviesListAdapter(final LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new MovieViewHolder(layoutInflater.inflate(ITEM_MOVIE_LAYOUT, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieViewHolder holder, final int position) {
        holder.render(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(final List<MovieViewModel> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieViewHolder(final View itemView) {
            super(itemView);
        }

        void render(final MovieViewModel movieViewModel) {
            final TextView textView = itemView.findViewById(MOVIE_NAME_TEXT_VIEW);
            textView.setText(movieViewModel.title);
        }
    }
}
