package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        var file1Data = fileData(filePath1);
        var file2Data = fileData(filePath2);
        var statuses = statuses(file1Data, file2Data);

        return ;
    }

    private static Map<String, Object> fileData(String filePath) throws Exception {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(path.toFile(), Map.class);
    }

    public static Map<String,String> statuses(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String,String> result = new LinkedHashMap<>();
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
