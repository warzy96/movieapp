package fiveagency.internship.food.movieapp.ui.pager;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public final class MoviePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> pages = new ArrayList<>();

    public MoviePagerAdapter(final FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(final int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return pages.get(position).getTag();
    }

    public void setPages(final List<Fragment> fragments) {
        this.pages.clear();
        this.pages.addAll(fragments);
        notifyDataSetChanged();
    }
}
