package fiveagency.internship.food.movieapp.ui.pager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;

public final class MoviePagerAdapter extends FragmentPagerAdapter {

    public MoviePagerAdapter(final FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(final int position) {
        switch (position) {
            case 0:
                return MoviesListFragment.newInstance();
            case 1:
                return MovieFavoritesFragment.newInstance();
            case 2:
                return DummyFragment1.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return "Page " + position;
    }
}
