package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        var file1Data = fileData(filePath1);
        var file2Data = fileData(filePath2);
        var keyStatuses = keyStatuses(file1Data, file2Data);

        if (keyStatuses.isEmpty()) {
            return "{}";
        }

        return buildString(file1Data, file2Data, keyStatuses);
    }

    private static String buildString(Map<String, Object> file1Data, Map<String, Object> file2Data,
                                      SortedMap<String, String> keyStatuses) throws Exception {
        var sb = new StringBuilder();
        sb.append('{');
        sb.append('\n');
        for (var keyStatus : keyStatuses.entrySet()) {
            appendBlock(sb, keyStatus, file1Data, file2Data);
        }
        sb.append('}');

        return sb.toString();
    }

    private static void appendBlock(StringBuilder sb, Map.Entry<String, String> keyStatus,
                                    Map<String, Object> file1Data, Map<String, Object> file2Data) throws Exception {
        var key = keyStatus.getKey();
        var status = keyStatus.getValue();
        if (status.equals("unchanged")) {
            appendLine(sb, ' ', key, file1Data.get(key).toString());
        } else if (status.equals("added")) {
            appendLine(sb, '+', key, file2Data.get(key).toString());
        } else if (status.equals("deleted")) {
            appendLine(sb, '-', key, file1Data.get(key).toString());
        } else if (status.equals("changed")) {
            appendLine(sb, '-', key, file1Data.get(key).toString());
            appendLine(sb, '+', key, file2Data.get(key).toString());
        } else {
            throw new Exception("unknown status");
        }
    }

    private static void appendLine(StringBuilder sb, Character symbol, String key, String value) {
        sb.append("  ");
        sb.append(symbol);
        sb.append(" ");
        sb.append(key);
        sb.append(": ");
        sb.append(value);
        sb.append('\n');
    }

    private static Map<String, Object> fileData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), Map.class);
    }

    public static SortedMap<String, String> keyStatuses(Map<String, Object> map1, Map<String, Object> map2) {
        SortedMap<String, String> keyStatuses = new TreeMap<>();
        for (var key : map1.keySet()) {
            if (map2.get(key) == null) {
                keyStatuses.put(key, "deleted");
            } else {
                keyStatuses.put(key, "unchanged");
            }
        }
        for (var key : map2.keySet()) {
            var map1Val = map1.get(key);
            if (map1Val == null) {
                keyStatuses.put(key, "added");
            } else if (!map1Val.equals(map2.get(key))) {
                keyStatuses.put(key, "changed");
            }
        }
        return keyStatuses;
    }
}
