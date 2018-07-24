package fiveagency.internship.food.movieapp.ui.pager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.injection.fragment.DaggerFragment;
import fiveagency.internship.food.movieapp.injection.fragment.FragmentComponent;
import fiveagency.internship.food.movieapp.ui.utils.StringUtil;

public final class ActivityFragment extends DaggerFragment {

    public static final String TAG = "ActivityFragment";
    private ViewPager viewPager;
    private TabLayout tabLayout;
    MoviePagerAdapter moviePagerAdapter;

    @Inject
    StringUtil stringUtil;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviePagerAdapter = new MoviePagerAdapter(getChildFragmentManager(), stringUtil);
    }

    @Override
    protected void inject(final FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pager_layout, container, false);
    }

    public static ActivityFragment newInstance() {
        return new ActivityFragment();
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(moviePagerAdapter);
        tabLayout = view.findViewById(R.id.main_pager_header);
        tabLayout.setupWithViewPager(viewPager);
    }
}
