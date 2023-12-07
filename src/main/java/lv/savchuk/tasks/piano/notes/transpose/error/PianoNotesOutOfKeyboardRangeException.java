package lv.savchuk.tasks.piano.notes.transpose.error;

import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNote;

import static java.lang.String.format;

/**
 * Exception to be thrown if a note falls out of the keyboard range.
 */
public class PianoNotesOutOfKeyboardRangeException extends PianoNotesGenericException {

    public PianoNotesOutOfKeyboardRangeException(PianoNote initialNote, byte transposeSemitones, PianoNote transposedNote) {
        super(format("Transposition to %d semitones for note %s is not possible as transposed note %s is out of the keyboard range.",
                transposeSemitones, initialNote, transposedNote));
    }

}