package fiveagency.internship.food.movieapp.ui.moviedetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.ui.base.BaseFragment;
import fiveagency.internship.food.movieapp.ui.utils.ImageLoader;

public final class MovieDetailsFragment extends BaseFragment<MovieDetailsContract.Presenter> implements MovieDetailsContract.View {

    public static final String TAG = "MovieDetailsFragment";
    private static final String KEY_MOVIE_ID = "key_movie_id";

    @BindView(R.id.movie_details_movie_name)
    AppCompatTextView movieDetailsMovieName;

    @BindView(R.id.movie_details_overview_text_view)
    AppCompatTextView movieDetailsMovieOverview;

    @BindView(R.id.movie_details_image_view)
    AppCompatImageView movieDetailsPoster;

    @BindView(R.id.movie_details_personal_note_text_view)
    AppCompatEditText movieDetailsPersonalNoteEditText;

    @BindView(R.id.movie_details_submit_button)
    AppCompatButton movieDetailsSubmitButton;

    @BindView(R.id.castRecyclerView)
    RecyclerView castRecyclerView;

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

        initRecyclerView();
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
        movieDetailsMovieName.setText(movieDetailsViewModel.title);
        movieDetailsMovieOverview.setText(movieDetailsViewModel.overview);
        movieDetailsPersonalNoteEditText.setText(movieDetailsViewModel.personalNote);
        imageLoader.renderImage(movieDetailsViewModel.imageSource, movieDetailsPoster, circularProgressbarStrokeWidth);
        movieDetailsSubmitButton.setOnClickListener(view -> {
            presenter.savePersonalNote(movieDetailsViewModel.withPersonalNote(movieDetailsPersonalNoteEditText.getText().toString()));
        });
        movieDetailsCastAdapter.setCast(movieDetailsViewModel.castList);
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }
}
