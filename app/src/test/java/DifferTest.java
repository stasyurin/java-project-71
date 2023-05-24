import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTest {

    public static final String EXPECTED_COMMON_CASE = "{\n"
            + "    chars1: [a, b, c]\n"
            + "  - chars2: [d, e, f]\n"
            + "  + chars2: false\n"
            + "  - checked: false\n"
            + "  + checked: true\n"
            + "  - default: null\n"
            + "  + default: [value1, value2]\n"
            + "  - id: 45\n"
            + "  + id: null\n"
            + "  - key1: value1\n"
            + "  + key2: value2\n"
            + "    numbers1: [1, 2, 3, 4]\n"
            + "  - numbers2: [2, 3, 4, 5]\n"
            + "  + numbers2: [22, 33, 44, 55]\n"
            + "  - numbers3: [3, 4, 5]\n"
            + "  + numbers4: [4, 5, 6]\n"
            + "  + obj1: {nestedKey=value, isNested=true}\n"
            + "  - setting1: Some value\n"
            + "  + setting1: Another value\n"
            + "  - setting2: 200\n"
            + "  + setting2: 300\n"
            + "  - setting3: true\n"
            + "  + setting3: none\n"
            + "}";
    public static final String EXPECTED_EMPTY = "{}";
    public static final String STYLISH = "stylish";

    @Test
    void testCommonCaseJSON() throws Exception {
        var actual = Differ.generate("./src/test/resources/file1.json",
                                     "./src/test/resources/file2.json",
                                     STYLISH);
        assertThat(actual).isEqualTo(EXPECTED_COMMON_CASE);
    }

    @Test
    void testEmptyJSON() throws Exception {
        var actual = Differ.generate("./src/test/resources/empty1.json",
                                     "./src/test/resources/empty2.json",
                                     STYLISH);
        assertThat(actual).isEqualTo(EXPECTED_EMPTY);
    }

    @Test
    void testCommonCaseYML() throws Exception {
        var actual = Differ.generate("./src/test/resources/file1.yml",
                                     "./src/test/resources/file2.yml",
                                     STYLISH);
        assertThat(actual).isEqualTo(EXPECTED_COMMON_CASE);
    }

    @Test
    void testEmptyYML() throws Exception {
        var actual = Differ.generate("./src/test/resources/empty1.yml",
                                     "./src/test/resources/empty2.yml",
                                     STYLISH);
        assertThat(actual).isEqualTo(EXPECTED_EMPTY);
    }
}
