package scs2682.finalproject.ui.home.events;

import scs2682.finalproject.data.note.NoteModel;

/**
 * WHen a contact needs to be created or updated
 */
public class OnNoteEditEvent {
	public final NoteModel noteModel;
	public final int noteModelPosition;

	/**
	 *
	 * @param noteModel Updated (or newly created) notes
	 * @param noteModelPosition Position of the notes to be updated.
	 * If a new contact, just return a negative value, e.g. -1
	 */
	public OnNoteEditEvent(NoteModel noteModel, int noteModelPosition) {
		this.noteModel = noteModel;
		this.noteModelPosition = noteModelPosition;
	}
}
