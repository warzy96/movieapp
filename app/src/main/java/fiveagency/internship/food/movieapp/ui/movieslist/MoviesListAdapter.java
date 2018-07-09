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

    public MoviesListAdapter(final LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new MovieViewHolder(layoutInflater.inflate(R.layout.item_movie, parent, false));
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
            final TextView textView = itemView.findViewById(R.id.movie_name);
            textView.setText(movieViewModel.title);
        }
    }
}
