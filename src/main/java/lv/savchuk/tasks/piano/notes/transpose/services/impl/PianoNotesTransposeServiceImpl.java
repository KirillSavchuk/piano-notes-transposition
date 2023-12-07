package lv.savchuk.tasks.piano.notes.transpose.services.impl;

import lv.savchuk.tasks.piano.notes.transpose.error.PianoNotesOutOfKeyboardRangeException;
import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNote;
import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNotesTransposeService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static lv.savchuk.tasks.piano.notes.transpose.contants.PianoNotesConstants.FIRST_NOTE;
import static lv.savchuk.tasks.piano.notes.transpose.contants.PianoNotesConstants.LAST_NOTE;

/**
 * Default implementation of {@link PianoNotesTransposeService}
 */
public class PianoNotesTransposeServiceImpl implements PianoNotesTransposeService {

    private static final Predicate<PianoNote> IS_NOTE_OUT_OF_RANGE_LEFT = note -> note.compareTo(FIRST_NOTE) < 0;
    private static final Predicate<PianoNote> IS_NOTE_OUT_OF_RANGE_RIGHT = note -> note.compareTo(LAST_NOTE) > 0;

    @Override
    public List<PianoNote> transpose(List<PianoNote> notes, byte transposeSemitones) throws PianoNotesOutOfKeyboardRangeException {
        final Predicate<PianoNote> noteOutOfRangeChecker = transposeSemitones > 0 ? IS_NOTE_OUT_OF_RANGE_RIGHT : IS_NOTE_OUT_OF_RANGE_LEFT;
        final Map<PianoNote, PianoNote> transposedNotesHistory = new HashMap<>();
        final List<PianoNote> transposedNotes = new ArrayList<>();
        for (PianoNote note : notes) {
            if (transposedNotesHistory.containsKey(note)) {
                transposedNotes.add(transposedNotesHistory.get(note));
            } else {
                final PianoNote transposedNote = transposeNote(note, transposeSemitones, noteOutOfRangeChecker);
                transposedNotesHistory.put(note, transposedNote);
                transposedNotes.add(transposedNote);
            }
        }
        return transposedNotes;
    }

    private PianoNote transposeNote(PianoNote note, byte transposeSemitones, Predicate<PianoNote> noteOutOfRangeChecker) throws PianoNotesOutOfKeyboardRangeException {
        final PianoNote transposedNote = note.transpose(transposeSemitones);
        if (noteOutOfRangeChecker.test(transposedNote)) {
            throw new PianoNotesOutOfKeyboardRangeException(note, transposeSemitones, transposedNote);
        } else {
            return transposedNote;
        }
    }

}