package lv.savchuk.tasks.piano.notes.transpose.services.api;

import static java.lang.String.format;
import static lv.savchuk.tasks.piano.notes.transpose.contants.PianoNotesConstants.OCTAVE_NOTES_COUNT;

/**
 * Piano Note that consists of {@code octaveNumber} and {@code noteNumber}.
 * Both values can represent the Note coordinate on a Piano, using a function {@link PianoNote#toNoteCoordinate()}, that can be used to find similar Note.
 */
public class PianoNote implements Comparable<PianoNote> {

    private final byte octaveNumber;
    private final byte noteNumber;

    public PianoNote(int octaveNumber, int noteNumber) {
        this.octaveNumber = (byte) octaveNumber;
        this.noteNumber = (byte) noteNumber;
    }

    public static PianoNote of(int noteCoordinate) {
        final int octaveNumber = Math.floorDiv(noteCoordinate, OCTAVE_NOTES_COUNT);
        return new PianoNote(octaveNumber, noteCoordinate - octaveNumber * OCTAVE_NOTES_COUNT);
    }

    public byte getOctaveNumber() {
        return octaveNumber;
    }

    public byte getNoteNumber() {
        return noteNumber;
    }

    public PianoNote transpose(byte transposeSemitones) {
        return of(toNoteCoordinate() + transposeSemitones);
    }

    private int toNoteCoordinate() {
        return octaveNumber * OCTAVE_NOTES_COUNT + noteNumber;
    }

    @Override
    public int compareTo(PianoNote compared) {
        return this.toNoteCoordinate() - compared.toNoteCoordinate();
    }

    @Override
    public int hashCode() {
        return toNoteCoordinate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PianoNote pianoNote = (PianoNote) o;
        return octaveNumber == pianoNote.octaveNumber && noteNumber == pianoNote.noteNumber;
    }

    @Override
    public String toString() {
        return format("[%d, %d]", octaveNumber, noteNumber);
    }

}