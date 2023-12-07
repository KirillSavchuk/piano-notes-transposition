package lv.savchuk.tasks.piano.notes.transpose.contants;

import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNote;

/**
 * Piano notes specific constants.
 */
public final class PianoNotesConstants {

    private PianoNotesConstants() {
        // Instantiation of utility class is forbidden.
    }

    public static final byte OCTAVE_NOTES_COUNT = 12;
    public static final PianoNote FIRST_NOTE = new PianoNote(-3, 10);
    public static final PianoNote LAST_NOTE = new PianoNote(5, 1);

}