package scs2682.finalproject.ui.home.mainboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import scs2682.finalproject.AppActivity;
import scs2682.finalproject.R;
import scs2682.finalproject.data.note.NoteModel;
import scs2682.finalproject.ui.home.events.OnNoteBackEvent;
import scs2682.finalproject.ui.home.events.OnNoteEditEvent;
import scs2682.finalproject.ui.home.events.OnNoteEvent;
import scs2682.finalproject.ui.home.events.OnNoteUpdatedEvent;

public class NoteFragment1 extends Fragment {
    public static final String NAME = NoteFragment1.class.getSimpleName();

    private TextView note_title;
    private TextView description;
    private LinearLayout fragment_layout;

    private String note_title_value;
    private String description_value;

    private NoteModel noteModel_fragment;
    private SharedPreferences fragmentData;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_fragment1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragment_layout = (LinearLayout) view;
        note_title = view.findViewById(R.id.note_title_frag1);
        description = view.findViewById(R.id.excerpt_frag1);

        // Stores shared preference values into local variable
        fragmentData = this.getActivity().getSharedPreferences("Fragment1", Context.MODE_PRIVATE);
        note_title_value = fragmentData.getString("NOTE_TITLE","Note 1");
        description_value = fragmentData.getString("DESCRIPTION","");

        // Sets values of all strings in fragment
        note_title.setText(note_title_value);
        description.setText(description_value);

        // Initializing NoteModel object to feed for fragment when clicked
        noteModel_fragment = new NoteModel(note_title_value, description_value);

        fragment_layout.setOnClickListener(v -> {
            // brings user to the 'Page' element containing the full text of the fragment
            EventBus.getDefault().post(new OnNoteEvent(noteModel_fragment, 1));

        });
    }

    @Subscribe
    public void onEventBusEvent(OnNoteUpdatedEvent event) {
        if (event.noteModelPosition == 1) {
            noteModel_fragment = event.noteModel;
            note_title.setText(noteModel_fragment.getNoteTitle());
            description.setText(noteModel_fragment.getDescription());

            this.getActivity().getSharedPreferences("Fragment1", Context.MODE_PRIVATE)
                    .edit()
                    .putString("NOTE_TITLE",event.noteModel.getNoteTitle())
                    .putString("DESCRIPTION", event.noteModel.getDescription())
                    .apply();
        }
        fragment_layout.animate().alpha(1.0f).setDuration(500);
        fragment_layout.setClickable(true);
    }

    @Subscribe
    public void onEventBusEvent(OnNoteEvent event) {
        fragment_layout.animate().alpha(0.0f).setDuration(100);
        fragment_layout.setClickable(false);
    }


    @Subscribe
    public void onEventBusEvent(OnNoteBackEvent event) {
        fragment_layout.animate().alpha(1.0f).setDuration(500);
        fragment_layout.setClickable(true);
    }

    @Override public void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }


}
