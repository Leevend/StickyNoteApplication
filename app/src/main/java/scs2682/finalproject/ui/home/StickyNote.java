package scs2682.finalproject.ui.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import scs2682.finalproject.AppActivity;
import scs2682.finalproject.R;
import scs2682.finalproject.data.note.NoteModel;
import scs2682.finalproject.ui.home.events.OnNoteBackEvent;
import scs2682.finalproject.ui.home.events.OnNoteEditEvent;
import scs2682.finalproject.ui.home.events.OnNoteEvent;
import scs2682.finalproject.ui.home.events.OnNoteUpdatedEvent;

public class StickyNote extends ScrollView {
    private TextView noteTitle;
    private TextView description;

    private NoteModel noteModel;
    private int noteModelPosition = 0;

    public StickyNote(Context context) {
        super(context);
    }
    public StickyNote(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public StickyNote(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Subscribe
    public void onEventBusEvent(OnNoteEvent event) {
        noteModel = event.noteModel;
        noteModelPosition = event.noteModelPosition;

        updateUi();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // this method will be called at the end of the layout inflation.
        // It is safe to look for any view children

        noteTitle = findViewById(R.id.noteTitleView);
        description = findViewById(R.id.descriptionView);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // post null as contact, so nothing will be added or updated
                EventBus.getDefault().post(new OnNoteBackEvent(null, -1));

            }
        });

        Button edit = findViewById(R.id.edit);
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // dispatch the event with updated contact and its position
                EventBus.getDefault().post(new OnNoteEditEvent(noteModel,
                        noteModelPosition));

                // reset UI
                noteModel = null;
                noteModelPosition = 0;
                updateUi();
            }

        });
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

    /**
     * Update UI from 'noteModel'
     */
    private void updateUi() {
        // default as empty, nothing
        String noteTitleValue = "";
        String descriptionValue = "";

        if (noteModel != null) {
            noteTitleValue = noteModel.getNoteTitle();
            descriptionValue = noteModel.getDescription();
        }

        noteTitle.setText(noteTitleValue);
        description.setText(descriptionValue);
    }


}
