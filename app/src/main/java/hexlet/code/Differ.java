package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        var file1Data = fileData(filePath1);
        var file2Data = fileData(filePath2);
        var diff = diff(file1Data, file2Data);

        return Formatter.format("stylish", diff);
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        var file1Data = fileData(filePath1);
        var file2Data = fileData(filePath2);
        var diff = diff(file1Data, file2Data);

        return Formatter.format(formatName, diff);
    }

    private static Map<String, Object> fileData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        return Parser.parse(path.toFile());
    }

    public static SortedMap<String, Map> diff(Map<String, Object> map1, Map<String, Object> map2) {
        SortedMap<String, Map> diff = new TreeMap<>();
        var commonKeySet = new HashSet<String>();
        commonKeySet.addAll(map1.keySet());
        commonKeySet.addAll(map2.keySet());
        for (var key : commonKeySet) {
            diff.put(key, keyData(key, map1, map2));
        }
        return diff;
    }

    private static Map<String, Object> keyData(String key, Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> keyData = new HashMap<>();
        if (!map1.containsKey(key)) {
            keyData.put("status", "added");
            keyData.put("value", map2.get(key));
        } else if (!map2.containsKey(key)) {
            keyData.put("status", "deleted");
            keyData.put("value", map1.get(key));
        } else if (Objects.equals(map1.get(key), map2.get(key))) {
            keyData.put("status", "unchanged");
            keyData.put("value", map1.get(key));
        } else {
            keyData.put("status", "changed");
            keyData.put("previous value", map1.get(key));
            keyData.put("current value", map2.get(key));
        }
        return keyData;
    }
}
