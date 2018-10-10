package scs2682.finalproject.ui.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Adapter to load all three pages in the view pager
 */

public class NoteAdapter extends PagerAdapter {
    private final List<NoteAdapterPage> pages;
    private final LayoutInflater layoutInflater;

    public NoteAdapter(Context context, List<NoteAdapterPage> pages) {
        this.pages = pages;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        NoteAdapterPage page = pages.get(position);
        return page.getTitle();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup viewPager, int position) {
        NoteAdapterPage page = pages.get(position);
        View view = layoutInflater.inflate(page.getLayoutId(),viewPager,false);

        // we have to add the view manually into the view pager

        viewPager.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup viewPager, int position, @NonNull Object pageView) {
        viewPager.removeView((View) pageView);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view;
    }


}
