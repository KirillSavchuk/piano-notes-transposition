package lv.savchuk.tasks.piano.notes.transpose.services.api;

import lv.savchuk.tasks.piano.notes.transpose.error.PianoNotesOutOfKeyboardRangeException;

import java.util.List;

/**
 * A service that performs {@link PianoNote} transposition.
 */
public interface PianoNotesTransposeService {

    /**
     * A service that performs {@link PianoNote} transposition.
     * @param notes Initial list of {@link PianoNote}
     * @param transposeSemitones The target semitones transposition value (can be negative)
     * @return List of transposed {@link PianoNote}
     * @throws PianoNotesOutOfKeyboardRangeException If transposed {@link PianoNote} went out of piano keyboard range.
     */
    List<PianoNote> transpose(List<PianoNote> notes, byte transposeSemitones) throws PianoNotesOutOfKeyboardRangeException;

}
