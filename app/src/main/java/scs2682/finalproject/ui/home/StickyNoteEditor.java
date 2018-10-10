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
import scs2682.finalproject.ui.home.events.OnNoteEditEvent;
import scs2682.finalproject.ui.home.events.OnNoteEvent;
import scs2682.finalproject.ui.home.events.OnNoteUpdatedEvent;

public class StickyNoteEditor extends ScrollView {
    private TextView noteTitle;
    private TextView description;

    private NoteModel noteModel;
    private int noteModelPosition = 0;

    public StickyNoteEditor(Context context) {
        super(context);
    }
    public StickyNoteEditor(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public StickyNoteEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Subscribe
    public void onEventBusEvent(OnNoteEditEvent event) {
        noteModel = event.noteModel;
        noteModelPosition = event.noteModelPosition;

        updateUi();

        AppActivity.showKeyboard(getContext(), noteTitle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // this method will be called at the end of the layout inflation.
        // It is safe to look for any view children

        noteTitle = findViewById(R.id.noteTitleEdit);
        description = findViewById(R.id.descriptionEdit);

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // post null as contact, so nothing will be added or updated
                EventBus.getDefault().post(new OnNoteEvent(noteModel, noteModelPosition));

                // reset UI
                noteModel = null;
                noteModelPosition = 0;
                updateUi();
            }
        });

        Button update = findViewById(R.id.update);
        update.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String noteTitleValue = noteTitle.getText().toString();

                    noteModel = new NoteModel(
                            noteTitleValue,
                            description.getText().toString());


                    // dispatch the event with updated contact and its position
                    EventBus.getDefault().post(new OnNoteUpdatedEvent(noteModel,
                            noteModelPosition));

                    // reset UI

                    noteModel = null;
                    noteModelPosition = 0;
                    updateUi();

            }
        });

        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                noteTitle.setText("");
                description.setText("");
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
