package fiveagency.internship.food.movieapp.ui.movieslist;

import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import fiveagency.internship.food.movieapp.R;

public final class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {

    private final LayoutInflater layoutInflater;
    private final List<MovieViewModel> movies = new ArrayList<>();
    private static final int ITEM_MOVIE_LAYOUT = R.layout.item_movie;
    private static final int MOVIE_NAME_TEXT_VIEW = R.id.movie_name;
    private static final int MOVIE_POSTER_IMAGE_VIEW = R.id.item_movie_poster_image;
    private static final int CIRCULAR_PROGRESS_DRAWABLE_STROKE_WIDTH = R.dimen.circular_progressbar_stroke_width;
    private MovieOnClickListener onMovieClickListener;
    private FavoriteOnChangeListener favoriteOnChangeListener;

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

    public void setOnMovieClickListener(final MovieOnClickListener movieClickListener) {
        onMovieClickListener = movieClickListener;
    }

    public void setFavoriteOnCheckedListener(final FavoriteOnChangeListener favoriteOnChangeListener) {
        this.favoriteOnChangeListener = favoriteOnChangeListener;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieViewHolder(final View itemView) {
            super(itemView);
        }

        void render(final MovieViewModel movieViewModel, final MovieOnClickListener movieOnClickListener, final FavoriteOnChangeListener favoriteOnCheckedListener) {
            final TextView movieTitleTextView = itemView.findViewById(MOVIE_NAME_TEXT_VIEW);
            movieTitleTextView.setText(movieViewModel.title);
            final ImageView imageView = itemView.findViewById(MOVIE_POSTER_IMAGE_VIEW);
            final CircularProgressDrawable circularProgressDrawable = initCircularProgressDrawable();
            Glide.with(itemView.getContext())
                 .load(movieViewModel.imageSource)
                 .apply(new RequestOptions().placeholder(circularProgressDrawable))
                 .into(imageView);
            itemView.setOnClickListener(view -> movieOnClickListener.onClick(movieViewModel.id));

            //TODO: put at the beginning of the class + use butterknife!
            final CheckBox starCheckBox = itemView.findViewById(R.id.item_movie_favorite_checkbox);
            if (movieViewModel.isFavorite) {
                starCheckBox.setChecked(true);
            } else {
                starCheckBox.setChecked(false);
            }
            starCheckBox.setOnCheckedChangeListener((compoundButton, b) -> {
                favoriteOnCheckedListener.onClick(movieViewModel.id, starCheckBox.isChecked());
            });
        }

        private CircularProgressDrawable initCircularProgressDrawable() {
            final CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(itemView.getContext());
            circularProgressDrawable.setStrokeWidth(itemView.getResources().getDimension(CIRCULAR_PROGRESS_DRAWABLE_STROKE_WIDTH));
            circularProgressDrawable.start();
            return circularProgressDrawable;
        }
    }

    public interface MovieOnClickListener {

        void onClick(int movieId);
    }

    public interface FavoriteOnChangeListener {

        void onClick(int movieId, boolean isChecked);
    }
}
