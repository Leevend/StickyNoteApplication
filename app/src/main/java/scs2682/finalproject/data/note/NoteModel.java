package scs2682.finalproject.data.note;

import android.os.Bundle;


public class NoteModel {
    private static final String NOTE_TITLE = "title";
    private static final String DESCRIPTION = "description";

    private final Bundle bundle;

    public NoteModel(String note_title, String description) {
        bundle = new Bundle();
        bundle.putString(NOTE_TITLE, note_title);
        bundle.putString(DESCRIPTION, description);
    }

    public NoteModel(Bundle bundle) {
        this.bundle = bundle;
    }

    public String getNoteTitle() {
        return bundle.getString(NOTE_TITLE, "");
    }

    public String getDescription() {
        return bundle.getString(DESCRIPTION, "");
    }

}
