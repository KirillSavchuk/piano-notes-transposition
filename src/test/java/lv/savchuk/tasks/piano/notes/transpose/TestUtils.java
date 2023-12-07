package lv.savchuk.tasks.piano.notes.transpose;

import java.io.IOException;
import java.util.Objects;

/**
 * Common testing utils.
 */
public class TestUtils {

    public String readFile(String filePath) throws IOException {
        return new String(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath)).readAllBytes());
    }

}