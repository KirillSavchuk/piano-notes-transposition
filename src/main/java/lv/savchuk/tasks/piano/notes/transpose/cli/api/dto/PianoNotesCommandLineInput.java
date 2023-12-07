package lv.savchuk.tasks.piano.notes.transpose.cli.api.dto;

import java.io.File;

import static picocli.CommandLine.Option;

/**
 * Application command line input DTO.
 */
public class PianoNotesCommandLineInput {

    @Option(names = {"-nf", "--notes-file"}, required = true, description = "a JSON file with a collection of notes")
    private File notesFile;

    @Option(names = {"-t", "--transpose"}, required = true, description = "a number of semitones to transpose")
    private byte transpose;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private boolean isHelpNeeded;

    public File getNotesFile() {
        return notesFile;
    }

    public byte getTranspose() {
        return transpose;
    }

    public boolean isHelpNeeded() {
        return isHelpNeeded;
    }

}