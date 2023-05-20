import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTest {

    @Test
    void testCommonCaseJSON() throws Exception {
        var expected = "{\n"
                + "    chars1: [a, b, c]"
                + "  - chars2: [d, e, f]"
                + "  + chars2: false"
                + "  - checked: false"
                + "  + checked: true"
                + "  - default: null"
                + "  + default: [value1, value2]"
                + "  - id: 45"
                + "  + id: null"
                + "  - key1: value1"
                + "  + key2: value2"
                + "    numbers1: [1, 2, 3, 4]"
                + "  - numbers2: [2, 3, 4, 5]"
                + "  + numbers2: [22, 33, 44, 55]"
                + "  - numbers3: [3, 4, 5]"
                + "  + numbers4: [4, 5, 6]"
                + "  + obj1: {nestedKey=value, isNested=true}"
                + "  - setting1: Some value"
                + "  + setting1: Another value"
                + "  - setting2: 200"
                + "  + setting2: 300"
                + "  - setting3: true"
                + "  + setting3: none"
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

    @Test
    void testCommonCaseYML() throws Exception {
        var expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        var actual = Differ.generate("./src/test/resources/file1.yml", "./src/test/resources/file2.yml");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testEmptyYMLYML() throws Exception {
        var expected = "{}";
        var actual = Differ.generate("./src/test/resources/empty1.yml", "./src/test/resources/empty2.yml");
        assertThat(actual).isEqualTo(expected);
    }
}
