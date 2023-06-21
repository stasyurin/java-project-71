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

    public static String format(String formatName, SortedMap<String, Map> diff) throws Exception {
        if (formatName.equals(STYLISH)) {
            return Stylish.buildString(diff);
        } else if (formatName.equals(PLAIN)) {
            return Plain.buildString(diff);
        } else if (formatName.equals(JSON)) {
            return Json.buildString(diff);
        } else {
            throw new Exception("Unknown format: " + formatName);
        }
    }
}
