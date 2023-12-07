package lv.savchuk.tasks.piano.notes.transpose.cli.api;

import lv.savchuk.tasks.piano.notes.transpose.cli.api.dto.PianoNotesCommandLineInput;
import lv.savchuk.tasks.piano.notes.transpose.cli.api.dto.PianoNotesCommandLineOutput;

/**
 * A service that acts like a manager between command line interface and piano notes transposition services.
 */
public interface PianoNotesCommandLineManager {

    PianoNotesCommandLineOutput transposeNotes(PianoNotesCommandLineInput input);

}
