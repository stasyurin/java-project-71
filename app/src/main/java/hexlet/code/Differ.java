package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Differ {
    private static final Map<String, Character> STATUSES_SYMBOLS = Map.of(
            "added", '+',
            "deleted", '-',
            "unchanged", ' ');

    public static String generate(String filePath1, String filePath2) throws Exception {
        var file1Data = fileData(filePath1);
        var file2Data = fileData(filePath2);
        var statuses = statuses(file1Data, file2Data);

        if (statuses.isEmpty()) {
            return "{}";
        }

        var sb = new StringBuilder();
        sb.append('{');
        sb.append('\n');
        for (var kv : statuses.entrySet()) {
            sb.append("  ");
            if (STATUSES_SYMBOLS.containsKey(kv.getValue())) {
                sb.append(STATUSES_SYMBOLS.get(kv.getValue()));
                sb.append(" ");
                sb.append(kv.getKey());
                sb.append(": ");
            }
            if (kv.getValue().equals("unchanged")) {
                sb.append(file1Data.get(kv.getKey()));
            } else if (kv.getValue().equals("added")) {
                sb.append(file2Data.get(kv.getKey()));
            } else if (kv.getValue().equals("deleted")) {
                sb.append(file1Data.get(kv.getKey()));
            } else if (kv.getValue().equals("changed")) {
                sb.append("- ");
                sb.append(kv.getKey());
                sb.append(": ");
                sb.append(file1Data.get(kv.getKey()));

                sb.append('\n');
                sb.append("  ");

                sb.append("+ ");
                sb.append(kv.getKey());
                sb.append(": ");
                sb.append(file2Data.get(kv.getKey()));
            } else {
                throw new Exception("unknown status");
            }
            sb.append('\n');
        }
        sb.append('}');

        return sb.toString();
    }

    private static Map<String, Object> fileData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), Map.class);
    }

    public static SortedMap<String, String> statuses(Map<String, Object> map1, Map<String, Object> map2) {
        SortedMap<String, String> result = new TreeMap<>();
        for (var key : map1.keySet()) {
            if (map2.get(key) == null) {
                result.put(key, "deleted");
            } else {
                result.put(key, "unchanged");
            }
        }
        for (var key : map2.keySet()) {
            var map1Val = map1.get(key);
            if (map1Val == null) {
                result.put(key, "added");
            } else if (!map1Val.equals(map2.get(key))) {
                result.put(key, "changed");
            }
        }
        return result;
    }
}
