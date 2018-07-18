package fiveagency.internship.food.movieapp.ui.favoriteslist;

import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.ui.movieslist.MovieViewModel;

public final class MovieFavoritesAdapter extends RecyclerView.Adapter<MovieFavoritesAdapter.MovieFavoriteViewHolder> {

    private final LayoutInflater layoutInflater;
    private FavoriteMovieOnClickListener favoriteMovieOnClickListener = movieId -> {};
    private static final int ITEM_MOVIE_LAYOUT = R.layout.item_movie;
    private static final int MOVIE_NAME_TEXT_VIEW = R.id.movie_name;
    private static final int MOVIE_POSTER_IMAGE_VIEW = R.id.item_movie_poster_image;
    private static final int CIRCULAR_PROGRESS_DRAWABLE_STROKE_WIDTH = R.dimen.circular_progressbar_stroke_width;
    private List<MovieViewModel> movies = new ArrayList<>();

    public MovieFavoritesAdapter(final LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public MovieFavoriteViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return new MovieFavoriteViewHolder(layoutInflater.inflate(ITEM_MOVIE_LAYOUT, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieFavoriteViewHolder holder, final int position) {
        holder.render(movies.get(position), favoriteMovieOnClickListener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(final List<MovieViewModel> movies) {
        this.movies = movies;
    }

    static class MovieFavoriteViewHolder extends RecyclerView.ViewHolder {

        public MovieFavoriteViewHolder(final View itemView) {
            super(itemView);
        }

        void render(final MovieViewModel movieViewModel, final FavoriteMovieOnClickListener favoriteMovieOnClickListener) {
            final TextView movieTitleTextView = itemView.findViewById(MOVIE_NAME_TEXT_VIEW);
            movieTitleTextView.setText(movieViewModel.title);
            final ImageView imageView = itemView.findViewById(MOVIE_POSTER_IMAGE_VIEW);
            final CircularProgressDrawable circularProgressDrawable = initCircularProgressDrawable();
            Glide.with(itemView.getContext())
                 .load(movieViewModel.imageSource)
                 .apply(new RequestOptions().placeholder(circularProgressDrawable))
                 .into(imageView);
            itemView.setOnClickListener(view -> favoriteMovieOnClickListener.onClick(movieViewModel.id));
        }

        private CircularProgressDrawable initCircularProgressDrawable() {
            final CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
            circularProgressDrawable.setStrokeWidth(itemView.getResources().getDimension(CIRCULAR_PROGRESS_DRAWABLE_STROKE_WIDTH));
            circularProgressDrawable.start();
            return circularProgressDrawable;
        }
    }

    public interface FavoriteMovieOnClickListener {

        void onClick(int movieId);
    }
}
