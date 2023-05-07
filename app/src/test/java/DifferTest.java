import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTest {

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
}
