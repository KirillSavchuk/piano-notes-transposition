package lv.savchuk.tasks.piano.notes.transpose;

import com.google.gson.GsonBuilder;
import lv.savchuk.tasks.piano.notes.transpose.cli.api.PianoNotesCommandLineManager;
import lv.savchuk.tasks.piano.notes.transpose.cli.api.dto.PianoNotesCommandLineInput;
import lv.savchuk.tasks.piano.notes.transpose.cli.api.dto.PianoNotesCommandLineOutput;
import lv.savchuk.tasks.piano.notes.transpose.cli.impl.PianoNotesCommandLineManagerImpl;
import lv.savchuk.tasks.piano.notes.transpose.services.impl.PianoNotesConverterServiceImpl;
import lv.savchuk.tasks.piano.notes.transpose.services.impl.PianoNotesTransposeServiceImpl;
import picocli.CommandLine;


public class PianoNotesTranspositionApp {

    public static void main(String... args) {
        final PianoNotesCommandLineInput inputArgs = parseInputArgs(args);
        final PianoNotesCommandLineManager cliManager = new PianoNotesCommandLineManagerImpl(
                new PianoNotesConverterServiceImpl(new GsonBuilder()),
                new PianoNotesTransposeServiceImpl()
        );

        final PianoNotesCommandLineOutput outputResult = cliManager.transposeNotes(inputArgs);
        if (outputResult.getException() == null) {
            System.out.println(outputResult.getTransposedNotes());
        } else {
            System.err.printf("An unexpected error occurred: %s", outputResult.getException().getMessage());
        }
    }

    private static PianoNotesCommandLineInput parseInputArgs(String... args) {
        final PianoNotesCommandLineInput inputArgs = new PianoNotesCommandLineInput();
        final CommandLine commandLine = new CommandLine(inputArgs);
        commandLine.parseArgs(args);
        if (inputArgs.isHelpNeeded()) {
            commandLine.usage(System.out);
            System.exit(0);
        }
        return inputArgs;
    }

}
