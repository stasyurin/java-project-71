package hexlet.code;

import java.util.Map;
import java.util.SortedMap;

public class Formatter {
    public static String format(String format, Map<String, Object> file1Data, Map<String, Object> file2Data,
                                SortedMap<String, String> keyStatuses) throws Exception {
        if (format.equals("stylish")) {
            return Stylish.buildString(file1Data, file2Data, keyStatuses);
        } else {
            throw new Exception("Unknown format: " + format);
        }
    }
}
