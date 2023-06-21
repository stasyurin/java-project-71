package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.Map;
import java.util.SortedMap;

public class Formatter {

    public static final String STYLISH = "stylish";
    public static final String PLAIN = "plain";
    public static final String JSON = "json";

    public static String format(String formatName, Map<String, Object> file1Data, Map<String, Object> file2Data,
                                SortedMap<String, String> keyStatuses) throws Exception {
        if (formatName.equals(STYLISH)) {
            return Stylish.buildString(file1Data, file2Data, keyStatuses);
        } else if (formatName.equals(PLAIN)) {
            return Plain.buildString(file1Data, file2Data, keyStatuses);
        } else if (formatName.equals(JSON)) {
            return Json.buildString(file1Data, file2Data, keyStatuses);
        } else {
            throw new Exception("Unknown format: " + formatName);
        }
    }
}
