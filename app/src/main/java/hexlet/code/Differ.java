package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        var file1Data = fileData(filePath1);
        var file2Data = fileData(filePath2);
        var keyStatuses = keyStatuses(file1Data, file2Data);

        if (keyStatuses.isEmpty()) {
            return "{}";
        }

        return Formatter.format(format, file1Data, file2Data, keyStatuses);
    }

    private static Map<String, Object> fileData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Parser.parse(path.toFile());
    }

    public static SortedMap<String, String> keyStatuses(Map<String, Object> map1, Map<String, Object> map2) {
        SortedMap<String, String> keyStatuses = new TreeMap<>();
        var commonKeySet = new HashSet<String>();
        commonKeySet.addAll(map1.keySet());
        commonKeySet.addAll(map2.keySet());
        for (var key : commonKeySet) {
            putKeyStatus(keyStatuses, key, map1, map2);
        }
        return keyStatuses;
    }

    private static void putKeyStatus(SortedMap<String, String> keyStatuses, String key, Map<String, Object> map1,
                                     Map<String, Object> map2) {
        if (!map1.containsKey(key)) {
            keyStatuses.put(key, "added");
        } else if (!map2.containsKey(key)) {
            keyStatuses.put(key, "deleted");
        } else if (Objects.equals(map1.get(key), map2.get(key))) {
            keyStatuses.put(key, "unchanged");
        } else {
            keyStatuses.put(key, "changed");
        }
    }
}
