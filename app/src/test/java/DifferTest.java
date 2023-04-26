import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    @Test
    void test1() throws Exception {
        var expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}";
        var actual = Differ.generate("./src/test/resources/file1.json", "./src/test/resources/file2.json");
        assertThat(actual).isEqualTo(expected);
    }
}
