import hexlet.code.Differ;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTest {
    @BeforeEach
    void createFile1() throws Exception {
        List<String> content = Arrays.asList(
                "{",
                "  \"host\": \"hexlet.io\",",
                "  \"timeout\": 50,",
                "  \"proxy\": \"123.234.53.22\",",
                "  \"follow\": false",
                "}");
        Path path = Paths.get("./src/test/resources/file1.json");
        try {
            Files.write(path, content);
            //...

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }
    @BeforeEach
    void createFile2() throws Exception {
        List<String> content = Arrays.asList(
                "{",
                "  \"timeout\": 20,",
                "  \"verbose\": true,",
                "  \"host\": \"hexlet.io\"",
                "}");
        Path path = Paths.get("./src/test/resources/file2.json");
        try {
            Files.write(path, content);
            //...

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }
    @BeforeEach
    void createEmptyFile1() throws Exception {
        List<String> content = Arrays.asList("{}");
        Path path = Paths.get("./src/test/resources/empty1.json");
        try {
            Files.write(path, content);
            //...

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }
    @BeforeEach
    void createEmptyFile2() throws Exception {
        List<String> content = Arrays.asList("{}");
        Path path = Paths.get("./src/test/resources/empty2.json");
        try {
            Files.write(path, content);
            //...

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }
    }

    @Test
    void testCommonCase() throws Exception {
        var expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        var actual = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testEmptyJSON() throws Exception {
        var expected = "{}";
        var actual = Differ.generate("./src/test/resources/empty1.json", "./src/test/resources/empty2.json");
        assertThat(actual).isEqualTo(expected);
    }


    @AfterEach
    void removeFiles() throws Exception {
        Files.deleteIfExists(Paths.get("./src/test/resources/file1.json"));
        Files.deleteIfExists(Paths.get("./src/test/resources/file2.json"));
        Files.deleteIfExists(Paths.get("./src/test/resources/empty1.json"));
        Files.deleteIfExists(Paths.get("./src/test/resources/empty2.json"));
    }
}
