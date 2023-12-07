package lv.savchuk.tasks.piano.notes.transpose.cli.impl;

import com.google.gson.JsonArray;
import lv.savchuk.tasks.piano.notes.transpose.cli.api.PianoNotesCommandLineManager;
import lv.savchuk.tasks.piano.notes.transpose.cli.api.dto.PianoNotesCommandLineInput;
import lv.savchuk.tasks.piano.notes.transpose.cli.api.dto.PianoNotesCommandLineOutput;
import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNote;
import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNotesConverterService;
import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNotesTransposeService;

import java.util.List;

/**
 * Default implementation of {@link PianoNotesCommandLineManager}
 *
 * @author ksavcuks
 */
public class PianoNotesCommandLineManagerImpl implements PianoNotesCommandLineManager {

    private final PianoNotesConverterService converterService;
    private final PianoNotesTransposeService transposeService;

    public PianoNotesCommandLineManagerImpl(PianoNotesConverterService converterService, PianoNotesTransposeService transposeService) {
        this.converterService = converterService;
        this.transposeService = transposeService;
    }

    @Override
    public PianoNotesCommandLineOutput transposeNotes(PianoNotesCommandLineInput input) {
        try {
            final List<PianoNote> pianoNotes = converterService.convertFromFile(input.getNotesFile());
            final List<PianoNote> transposedPianoNotes = transposeService.transpose(pianoNotes, input.getTranspose());
            final JsonArray jsonTransposedPianoNotes = converterService.convertToJsonArray(transposedPianoNotes);
            return new PianoNotesCommandLineOutput(jsonTransposedPianoNotes.toString());
        } catch (Exception exception) {
            return new PianoNotesCommandLineOutput(exception);
        }
    }

}