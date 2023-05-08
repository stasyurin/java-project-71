package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Parser {

    public static final String JSON = "json";
    public static final String YML = "yml";

    public static Map<String, Object> parse(File file) throws Exception {
        var extension = FilenameUtils.getExtension(file.getName());
        ObjectMapper mapper;
        if (extension.equals(JSON)) {
            mapper = new ObjectMapper();
        } else if (extension.equals(YML)) {
            mapper = new YAMLMapper();
        } else {
            throw new Exception("unknown file extension");
        }
        try {
            return mapper.readValue(file, Map.class);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

}
