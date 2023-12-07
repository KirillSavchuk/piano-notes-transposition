package lv.savchuk.tasks.piano.notes.transpose.services.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import lv.savchuk.tasks.piano.notes.transpose.error.PianoNotesGenericException;
import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNote;
import lv.savchuk.tasks.piano.notes.transpose.services.api.PianoNotesConverterService;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import static java.lang.String.format;

/**
 * Default implementation of {@link PianoNotesConverterService}
 */
public class PianoNotesConverterServiceImpl implements PianoNotesConverterService {

    private final Gson gson;

    public PianoNotesConverterServiceImpl(GsonBuilder gsonBuilder) {
        this.gson = gsonBuilder
                .registerTypeAdapter(PianoNote.class, new PianoNoteDeserializer())
                .registerTypeAdapter(PianoNote.class, new PianoNoteSerializer())
                .create();
    }

    @Override
    public List<PianoNote> convertFromFile(File notesFile) throws PianoNotesGenericException {
        try (Reader reader = new FileReader(notesFile)) {
            return gson.fromJson(reader, new TypeToken<List<PianoNote>>() {
            }.getType());
        } catch (Exception ex) {
            final String errorMessage = format("Failed to read JSON from a file: %s", ex.getMessage());
            throw new PianoNotesGenericException(errorMessage, ex);
        }

    }

    @Override
    public JsonArray convertToJsonArray(List<PianoNote> notes) {
        return gson.toJsonTree(notes).getAsJsonArray();
    }

    private static class PianoNoteDeserializer implements JsonDeserializer<PianoNote> {
        @Override
        public PianoNote deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            final JsonArray jsonArray = json.getAsJsonArray();
            final byte octaveNumber = jsonArray.get(0).getAsByte();
            final byte noteNumber = jsonArray.get(1).getAsByte();
            return new PianoNote(octaveNumber, noteNumber);
        }
    }

    private static class PianoNoteSerializer implements JsonSerializer<PianoNote> {
        @Override
        public JsonElement serialize(PianoNote pianoNote, Type typeOfSrc, JsonSerializationContext context) {
            final JsonArray notesJson = new JsonArray(2);
            notesJson.add(pianoNote.getOctaveNumber());
            notesJson.add(pianoNote.getNoteNumber());
            return notesJson;
        }
    }

}