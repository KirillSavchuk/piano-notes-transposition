package lv.savchuk.tasks.piano.notes.transpose.cli.api.dto;

/**
 * Application command line output DTO.
 */
public class PianoNotesCommandLineOutput {

    private String transposedNotes;
    private Throwable exception;

    public PianoNotesCommandLineOutput(String transposedNotes) {
        this.transposedNotes = transposedNotes;
    }

    public PianoNotesCommandLineOutput(Throwable exception) {
        this.exception = exception;
    }

    public String getTransposedNotes() {
        return transposedNotes;
    }

    public Throwable getException() {
        return exception;
    }

}