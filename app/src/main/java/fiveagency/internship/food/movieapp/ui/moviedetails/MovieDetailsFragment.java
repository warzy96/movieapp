package fiveagency.internship.food.movieapp.ui.moviedetails;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.domain.model.Rating;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.ui.base.BaseFragment;
import fiveagency.internship.food.movieapp.ui.moviedetails.beer.BeerDetailsDialog;
import fiveagency.internship.food.movieapp.ui.moviedetails.beer.BeerViewModel;
import fiveagency.internship.food.movieapp.ui.movieslist.CheckableFloatingActionButton;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;

public final class MovieDetailsFragment extends BaseFragment<MovieDetailsContract.Presenter> implements MovieDetailsContract.View {

    public static final String TAG = "MovieDetailsFragment";
    private static final String KEY_MOVIE_ID = "key_movie_id";

    @BindView(R.id.movie_details_overview_text_view)
    AppCompatTextView movieDetailsMovieOverview;

    @BindView(R.id.tmdbRatingText)
    AppCompatTextView tmdbRatingText;

    @BindView(R.id.movie_details_image_view)
    AppCompatImageView movieDetailsPoster;

    @BindView(R.id.movie_details_personal_note_text_view)
    AppCompatEditText movieDetailsPersonalNoteEditText;

    @BindView(R.id.movie_details_submit_button)
    AppCompatButton movieDetailsSubmitButton;

    @BindView(R.id.castRecyclerView)
    RecyclerView castRecyclerView;

    @BindView(R.id.favoriteFab)
    CheckableFloatingActionButton favoriteFloatingActionButton;

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.imdbRatingText)
    TextView imdbRatingText;

    @BindView(R.id.youtubePlayer)
    YouTubePlayerView youtubePlayer;

    @BindView(R.id.rottenTomatoesRatingText)
    TextView rottenTomatoesRatingText;

    @BindView(R.id.arrowBack)
    ImageView arrowBack;

    @BindView(R.id.beerIcon)
    ImageView beerIcon;

    @BindDimen(R.dimen.circular_progressbar_stroke_width)
    float circularProgressbarStrokeWidth;

    @Inject
    ImageLoader imageLoader;

    @Inject
    MovieDetailsCastAdapter movieDetailsCastAdapter;

    @Inject
    MovieDetailsContract.Presenter presenter;

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
        presenter.setView(this);
    }

    @NonNull
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

        arrowBack.setOnClickListener(back -> presenter.goBack());
        beerIcon.setOnClickListener(icon -> presenter.recommendBeer());
        initRecyclerView();

        initCollapsingToolbarTitle();
    }

    @Override
    public void onResume() {
        super.onResume();
        setStatusBarColor(R.color.transparent);
    }

    private void initCollapsingToolbarTitle() {
        collapsingToolbarLayout.setExpandedTitleTypeface(Typeface.create(collapsingToolbarLayout.getExpandedTitleTypeface(), Typeface.BOLD));
        collapsingToolbarLayout.setCollapsedTitleTypeface(Typeface.create(collapsingToolbarLayout.getCollapsedTitleTypeface(), Typeface.BOLD));
    }

    private void initRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        castRecyclerView.setLayoutManager(layoutManager);

        castRecyclerView.setAdapter(movieDetailsCastAdapter);
    }

    @Override
    public void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public void render(final MovieDetailsViewModel movieDetailsViewModel) {
        collapsingToolbarLayout.setTitle(movieDetailsViewModel.title);
        movieDetailsMovieOverview.setText(movieDetailsViewModel.overview);
        movieDetailsPersonalNoteEditText.setText(movieDetailsViewModel.personalNote);
        imageLoader.renderImage(movieDetailsViewModel.backdropPath, movieDetailsPoster, circularProgressbarStrokeWidth);
        movieDetailsSubmitButton.setOnClickListener(
                view -> presenter.savePersonalNote(movieDetailsViewModel.withPersonalNote(movieDetailsPersonalNoteEditText.getText().toString()))
        );
        movieDetailsCastAdapter.setOnCastClick(castId -> presenter.showActorDetailsScreen(castId));
        movieDetailsCastAdapter.setCast(movieDetailsViewModel.castList);
        tmdbRatingText.setText(parseRating(movieDetailsViewModel.tmdbRating));
        favoriteFloatingActionButton.setChecked(movieDetailsViewModel.isFavorite);
        favoriteFloatingActionButton.setOnCheckedChangeListener(isChecked -> {
            if (isChecked) {
                presenter.insertFavorite(movieDetailsViewModel.id);
            } else {
                presenter.removeFavorite(movieDetailsViewModel.id);
            }
        });
        getLifecycle().addObserver(youtubePlayer);
        if (!movieDetailsViewModel.videos.isEmpty()) {
            youtubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {

                @Override
                public void onReady(@NotNull final YouTubePlayer thisYoutubePlayer) {
                    thisYoutubePlayer.loadVideo(movieDetailsViewModel.videos.get(0).getKey(), 0);
                    thisYoutubePlayer.pause();
                }
            });
        } else {
            youtubePlayer.setEnabled(false);
        }
    }

    @Override
    public void renderRatings(final List<Rating> ratings) {
        for (final Rating rating : ratings) {
            if (rating.getSource().equals(Rating.IMDB_SOURCE)) {
                imdbRatingText.setText(rating.getValue());
            } else if (rating.getSource().equals(Rating.ROTTEN_TOMATOES_SOURCE)) {
                rottenTomatoesRatingText.setText(rating.getValue());
            }
        }
    }

    @Override
    public void showRecommendBeerDialog(final BeerViewModel beerViewModel) {
        final BeerDetailsDialog beerDetailsDialog = new BeerDetailsDialog(getContext());
        beerDetailsDialog.setBeerViewModel(beerViewModel, imageLoader);
        beerDetailsDialog.show();
    }

    @Override
    public void onDestroy() {
        setStatusBarColor(R.color.tabBackgroundColor);
        super.onDestroy();
    }

    private String parseRating(final float rating) {
        return rating + "/10";
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
