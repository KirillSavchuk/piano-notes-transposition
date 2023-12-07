package lv.savchuk.tasks.piano.notes.transpose.services.api;

import com.google.gson.JsonArray;
import lv.savchuk.tasks.piano.notes.transpose.error.PianoNotesGenericException;

import java.io.File;
import java.util.List;

/**
 * A service to convert {@link PianoNote} object to/from specific formats.
 */
public interface PianoNotesConverterService {

    List<PianoNote> convertFromFile(File notesFile) throws PianoNotesGenericException;

    JsonArray convertToJsonArray(List<PianoNote> notes);

}
