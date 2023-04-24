package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws Exception {
        var file1Data = fileData(filePath1);
        var file2Data = fileData(filePath2);

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
}
