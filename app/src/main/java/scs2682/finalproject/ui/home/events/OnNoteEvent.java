package scs2682.finalproject.ui.home.events;

import scs2682.finalproject.data.note.NoteModel;

/**
 * Dispatched when a note needs to be created or updated
 */
public final class OnNoteEvent {
	/**
	 * If null will treat as creating a brand new contact
	 */
	public final NoteModel noteModel;
	public final int noteModelPosition;

	/**
	 * @param noteModelPosition Position of the note in the dataset. If a negative value will treat as new
	 */
	public OnNoteEvent(NoteModel noteModel, int noteModelPosition) {
		this.noteModel = noteModel;
		this.noteModelPosition = noteModelPosition;
	}
}
