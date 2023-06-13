import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class DifferTest {

    public static final String EXPECTED_COMMON_CASE_STYLISH = "{\n"
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
            + "}\n";
    public static final String EXPECTED_COMMON_CASE_PLAIN
            = "Property 'chars2' was updated. From [complex value] to false\n"
            + "Property 'checked' was updated. From false to true\n"
            + "Property 'default' was updated. From null to [complex value]\n"
            + "Property 'id' was updated. From 45 to null\n"
            + "Property 'key1' was removed\n"
            + "Property 'key2' was added with value: 'value2'\n"
            + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
            + "Property 'numbers3' was removed\n"
            + "Property 'numbers4' was added with value: [complex value]\n"
            + "Property 'obj1' was added with value: [complex value]\n"
            + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
            + "Property 'setting2' was updated. From 200 to 300\n"
            + "Property 'setting3' was updated. From true to 'none'\n";
    public static final String EXPECTED_EMPTY_STYLISH = "{}";
    public static final String EXPECTED_EMPTY_PLAIN = "";
    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";

    @Test
    void testCommonCaseJSON() throws Exception {
        var actual = Differ.generate("./src/test/resources/file1.json",
                                     "./src/test/resources/file2.json",
                                     STYLISH);
        assertThat(actual).isEqualTo(EXPECTED_COMMON_CASE_STYLISH);
    }

    @Test
    void testEmptyJSON() throws Exception {
        var actual = Differ.generate("./src/test/resources/empty1.json",
                                     "./src/test/resources/empty2.json",
                                     STYLISH);
        assertThat(actual).isEqualTo(EXPECTED_EMPTY_STYLISH);
    }

    @Test
    void testCommonCaseYML() throws Exception {
        var actual = Differ.generate("./src/test/resources/file1.yml",
                                     "./src/test/resources/file2.yml",
                                     STYLISH);
        assertThat(actual).isEqualTo(EXPECTED_COMMON_CASE_STYLISH);
    }

    @Test
    void testEmptyYML() throws Exception {
        var actual = Differ.generate("./src/test/resources/empty1.yml",
                                     "./src/test/resources/empty2.yml",
                                     STYLISH);
        assertThat(actual).isEqualTo(EXPECTED_EMPTY_STYLISH);
    }

    @Test
    void testPlainFormat() throws Exception {
        var actual = Differ.generate("./src/test/resources/file1.json",
                                     "./src/test/resources/file2.json",
                                     PLAIN);
        assertThat(actual).isEqualTo(EXPECTED_COMMON_CASE_PLAIN);
    }

    @Test
    void testEmptyPlainFormat() throws Exception {
        var actual = Differ.generate("./src/test/resources/empty1.json",
                                     "./src/test/resources/empty2.json",
                                     PLAIN);
        assertThat(actual).isEqualTo(EXPECTED_EMPTY_PLAIN);
    }
}
