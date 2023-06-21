package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.SortedMap;

public class Json {
    public static String buildString(Map<String, Object> file1Data, Map<String, Object> file2Data,
                                     SortedMap<String, String> keyStatuses) throws Exception {
        if (keyStatuses.isEmpty()) {
            return "{}";
        }

        return new ObjectMapper().writeValueAsString(keyStatuses);
    }
}
