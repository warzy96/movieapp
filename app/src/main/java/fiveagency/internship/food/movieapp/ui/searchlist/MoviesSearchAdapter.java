package fiveagency.internship.food.movieapp.ui.searchlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModel;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;

public final class MoviesSearchAdapter extends RecyclerView.Adapter<MoviesSearchAdapter.MovieViewHolder> {

    private final LayoutInflater layoutInflater;
    private final ImageLoader imageLoader;
    private List<MovieViewModel> movies = new ArrayList<>();
    @LayoutRes
    private static final int ITEM_MOVIE_LAYOUT = R.layout.item_movie;
    private MoviesSearchAdapter.MovieOnClickListener onMovieClickListener = id -> {};
    private MoviesSearchAdapter.FavoriteOnChangeListener favoriteOnChangeListener = (id, isChecked) -> {};

    public MoviesSearchAdapter(final LayoutInflater layoutInflater, final ImageLoader imageLoader) {
        this.layoutInflater = layoutInflater;
        this.imageLoader = imageLoader;
    }

    @NonNull
    @Override
    public MoviesSearchAdapter.MovieViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new MoviesSearchAdapter.MovieViewHolder(layoutInflater.inflate(ITEM_MOVIE_LAYOUT, parent, false), imageLoader);
    }

    @Override
    public void onBindViewHolder(@NonNull final MoviesSearchAdapter.MovieViewHolder holder, final int position) {
        holder.render(movies.get(position), onMovieClickListener, favoriteOnChangeListener);
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

    public void setOnMovieClickListener(final MoviesSearchAdapter.MovieOnClickListener movieClickListener) {
        onMovieClickListener = movieClickListener;
    }

    public void setFavoriteOnCheckedListener(final MoviesSearchAdapter.FavoriteOnChangeListener favoriteOnChangeListener) {
        this.favoriteOnChangeListener = favoriteOnChangeListener;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_name)
        TextView movieItemTitleView;

        @BindView(R.id.item_movie_poster_image)
        ImageView movieItemPosterView;

        private ImageLoader imageLoader;

        @BindDimen(R.dimen.circular_progressbar_stroke_width)
        float circularProgressbarStrokeWidth;

        @BindView(R.id.item_movie_favorite_checkbox)
        CheckBox starCheckBox;

        MovieViewHolder(final View itemView, final ImageLoader imageLoader) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.imageLoader = imageLoader;
        }

        void render(final MovieViewModel movieViewModel, final MoviesSearchAdapter.MovieOnClickListener movieOnClickListener,
                    final MoviesSearchAdapter.FavoriteOnChangeListener favoriteOnCheckedListener) {
            starCheckBox.setOnCheckedChangeListener(null);
            movieItemTitleView.setText(movieViewModel.title);
            imageLoader.renderImage(movieViewModel.imageSource, movieItemPosterView, circularProgressbarStrokeWidth);
            itemView.setOnClickListener(view -> movieOnClickListener.onClick(movieViewModel.id));
            if (movieViewModel.isFavorite) {
                starCheckBox.setChecked(true);
            } else {
                starCheckBox.setChecked(false);
            }
            starCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> favoriteOnCheckedListener.onClick(movieViewModel, isChecked));
        }
    }

    public interface MovieOnClickListener {

        void onClick(int movieId);
    }

    public interface FavoriteOnChangeListener {

        void onClick(MovieViewModel movie, boolean isChecked);
    }
}
