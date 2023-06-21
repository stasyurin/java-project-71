package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.SortedMap;

public class Json {
    public static String buildString(SortedMap<String, Map> diff) throws Exception {
        if (diff.isEmpty()) {
            return "{}";
        }

        return new ObjectMapper().writeValueAsString(diff);
    }
}
