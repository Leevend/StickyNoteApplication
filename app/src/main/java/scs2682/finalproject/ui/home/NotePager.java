package scs2682.finalproject.ui.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import scs2682.finalproject.R;
import scs2682.finalproject.ui.home.events.OnNoteBackEvent;
import scs2682.finalproject.ui.home.events.OnNoteEditEvent;
import scs2682.finalproject.ui.home.events.OnNoteEvent;
import scs2682.finalproject.ui.home.events.OnNoteUpdatedEvent;

public class NotePager extends NonSwipeableViewPager {
    private static final int POSITION_MAIN = 0;
    private static final int POSITION_STICKY_NOTE = 1;
    private static final int POSITION_STICKY_NOTE_EDITOR = 2;

    public NotePager(Context context) {
        this(context, null);
    }

    public NotePager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Subscribe
    public void onEventBusEvent(OnNoteEvent event) {
        // moves to note viewing page
        setCurrentItem(POSITION_STICKY_NOTE);
    }

    @Subscribe
    public void onEventBusEvent(OnNoteUpdatedEvent event) {
        // move to main page
        setCurrentItem(POSITION_MAIN);
    }

    @Subscribe
    public void onEventBusEvent(OnNoteBackEvent event) {
        // move to main page
        setCurrentItem(POSITION_MAIN);
    }

    @Subscribe
    public void onEventBusEvent(OnNoteEditEvent event) {
        // move to note editing page
        setCurrentItem(POSITION_STICKY_NOTE_EDITOR);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        List<NoteAdapterPage> pages = new ArrayList<>();
        pages.add(POSITION_MAIN, new NoteAdapterPage(R.layout.main,
                getContext().getString(R.string.main)));
        pages.add(POSITION_STICKY_NOTE, new NoteAdapterPage(R.layout.stickynote,
                getContext().getString(R.string.note_viewer)));
        pages.add(POSITION_STICKY_NOTE_EDITOR, new NoteAdapterPage(R.layout.stickynote_editor,
                getContext().getString(R.string.note_editor)));

        setAdapter(new NoteAdapter(getContext(), pages));
    }


}