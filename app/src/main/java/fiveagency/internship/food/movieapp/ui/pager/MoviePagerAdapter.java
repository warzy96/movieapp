package fiveagency.internship.food.movieapp.ui.pager;

import com.annimon.stream.function.Supplier;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import fiveagency.internship.food.movieapp.R;
import fiveagency.internship.food.movieapp.ui.favoriteslist.MovieFavoritesFragment;
import fiveagency.internship.food.movieapp.ui.movieslist.MoviesListFragment;
import fiveagency.internship.food.movieapp.ui.searchlist.MoviesSearchFragment;
import fiveagency.internship.food.movieapp.ui.utils.StringUtil;

public final class MoviePagerAdapter extends FragmentPagerAdapter {

    //Titles and suppliers should be inserted in the same order
    private final List<Integer> titles = new ArrayList<>();
    private final List<Supplier<Fragment>> fragmentSuppliers = new ArrayList<>();

    private final StringUtil stringUtil;

    public MoviePagerAdapter(final FragmentManager fragmentManager, StringUtil stringUtil) {
        super(fragmentManager);
        this.stringUtil = stringUtil;
        fragmentSuppliers.add(MoviesListFragment::newInstance);
        fragmentSuppliers.add(MovieFavoritesFragment::newInstance);
        fragmentSuppliers.add(MoviesSearchFragment::newInstance);
        titles.add(R.string.title_movies_list);
        titles.add(R.string.title_movies_favorites);
        titles.add(R.string.title_movies_search);
    }

    @Override
    public Fragment getItem(final int position) {
        if (position > fragmentSuppliers.size() - 1) {
            throw new RuntimeException("Position is greater than the number of items!");
        }
        return fragmentSuppliers.get(position).get();
    }

    @Override
    public int getCount() {
        return fragmentSuppliers.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return stringUtil.getStringResource(titles.get(position));
    }
}
