package fiveagency.internship.food.movieapp.ui.movieslist.diffutil;

import android.support.v7.util.DiffUtil;

public final class MovieDiffUtilCallback<T extends DiffUtilViewModel> extends DiffUtil.ItemCallback<T> {

    @Override
    public boolean areItemsTheSame(final T oldItem, final T newItem) {
        return oldItem == null && newItem == null || !(oldItem == null || newItem == null) && oldItem.id.equals(newItem.id);
    }

    @Override
    public boolean areContentsTheSame(final T oldItem, final T newItem) {
        return oldItem == null && newItem == null || !(oldItem == null || newItem == null) && oldItem.equals(newItem);
    }
}
