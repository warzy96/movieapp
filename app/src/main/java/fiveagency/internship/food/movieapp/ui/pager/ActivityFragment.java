package fiveagency.internship.food.movieapp.ui.pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.ui.base.BaseFragment;
import fiveagency.internship.food.movieapp.ui.utils.StringUtil;

public final class ActivityFragment extends BaseFragment<ActivityContract.Presenter> implements ActivityContract.View {

    public static final String TAG = "ActivityFragment";

    @Inject
    StringUtil stringUtil;

    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_activity, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this);

        setUpBottomNavigation();

        presenter.showRecommendedMovies();
    }

    @Override
    public void onResume() {
        super.onResume();

        setStatusBarColor(R.color.transparent);

        presenter.start();
    }

    @Override
    public void render() {
        bottomNavigationView.setSelectedItemId(R.id.bottomNavigationRecommended);
    }

    private void setUpBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.bottomNavigationProfile: {
                    presenter.showUserProfileScreen();
                    return true;
                }
                case R.id.bottomNavigationRecommended: {
                    presenter.showRecommendedMovies();
                    return true;
                }
                case R.id.bottomNavigationFavorites: {
                    presenter.showFavoriteMovies();
                    return true;
                }
            }
            return false;
        });
    }

    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }
}
