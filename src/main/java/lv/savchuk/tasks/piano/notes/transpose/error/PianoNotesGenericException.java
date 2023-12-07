package lv.savchuk.tasks.piano.notes.transpose.error;

/**
 * A generic piano notes service exception.
 */
public class PianoNotesGenericException extends Exception {

    public PianoNotesGenericException(String message) {
        super(message);
    }

    public PianoNotesGenericException(String message, Throwable exception) {
        super(message, exception);
    }

}