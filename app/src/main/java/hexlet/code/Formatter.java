package hexlet.code;

import hexlet.code.formats.Stylish;

import java.util.Map;
import java.util.SortedMap;

public class Formatter {

    public static final String STYLISH = "stylish";

    public static String format(String formatName, Map<String, Object> file1Data, Map<String, Object> file2Data,
                                SortedMap<String, String> keyStatuses) throws Exception {
        if (formatName.equals(STYLISH)) {
            return Stylish.buildString(file1Data, file2Data, keyStatuses);
        } else {
            throw new Exception("Unknown format: " + formatName);
        }
    }
}
