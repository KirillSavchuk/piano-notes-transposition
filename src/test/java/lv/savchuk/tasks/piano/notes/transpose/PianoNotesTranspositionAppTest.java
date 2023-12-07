package lv.savchuk.tasks.piano.notes.transpose;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

class PianoNotesTranspositionAppTest {

    private final static String TEST_CASE_DIRECTORY = "src/test/resources";
    private final static String INPUT_FILE = "input.txt";
    private final static String OUTPUT_FILE = "output.txt";
    private final static String TRANSPOSE_VALUE_FILE = "transpose.txt";

    private final TestUtils testUtils = new TestUtils();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void main_error_fileDoesNotExist() {
        final String expectedErrorMsg = "An unexpected error occurred: Failed to read JSON from a file: dummy.json (No such file or directory)";

        PianoNotesTranspositionApp.main(createArgs("dummy.json", 5));

        assertThat(outContent.toString()).isEmpty();
        assertThat(errContent.toString()).isEqualTo(expectedErrorMsg);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-transpose-out-of-range-left", "2-transpose-out-of-range-right"})
    void main_error(String testCaseFolder) throws IOException {
        executeTestCase(testCaseFolder, false);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1-one-element", "2-transpose-zero", "3-large"})
    void main_success(String testCaseFolder) throws IOException {
        executeTestCase(testCaseFolder, true);
    }

    private void executeTestCase(String testCaseFolder, boolean isSuccessful) throws IOException {
        final String testCaseType = isSuccessful ? "success" : "error";
        final String initialNotesFile = getFilePath(TEST_CASE_DIRECTORY, testCaseType, testCaseFolder, INPUT_FILE);
        final byte transposeValue = Byte.parseByte(testUtils.readFile(getFilePath(testCaseType, testCaseFolder, TRANSPOSE_VALUE_FILE)));
        final String expectedResponse = testUtils.readFile(getFilePath(testCaseType, testCaseFolder, OUTPUT_FILE));

        PianoNotesTranspositionApp.main(createArgs(initialNotesFile, transposeValue));

        if (isSuccessful) {
            assertThat(outContent.toString().trim()).isEqualTo(expectedResponse.trim());
            assertThat(errContent.toString()).isEmpty();
        } else {
            assertThat(outContent.toString()).isEmpty();
            assertThat(errContent.toString()).isEqualTo(expectedResponse.trim());
        }
    }

    private String[] createArgs(String fileName, int transposeSemitones) {
        final String args = format("-nf %s -t %d", fileName, transposeSemitones);
        return args.split(" ");
    }

    private String getFilePath(String... path) {
        return String.join("/", path);
    }

}