package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class Plain {
    public static String buildString(Map<String, Object> file1Data, Map<String, Object> file2Data,
                                     SortedMap<String, String> keyStatuses) throws Exception {
        if (keyStatuses.isEmpty()) {
            return "";
        }

        var sb = new StringBuilder();
        for (var keyStatus : keyStatuses.entrySet()) {
            appendBlock(sb, keyStatus, file1Data, file2Data);
        }

        return sb.toString();
    }

    private static void appendBlock(StringBuilder sb, Map.Entry<String, String> keyStatus,
                                    Map<String, Object> file1Data, Map<String, Object> file2Data) throws Exception {
        var key = keyStatus.getKey();
        var status = keyStatus.getValue();
        if (status.equals("unchanged")) {
            return;
        } else if (status.equals("added")) {
            appendLine(sb, "added", key, stringValueOf(file2Data.get(key)), stringValueOf(file1Data.get(key)));
        } else if (status.equals("deleted")) {
            appendLine(sb, "removed", key, stringValueOf(file2Data.get(key)), stringValueOf(file1Data.get(key)));
        } else if (status.equals("changed")) {
            appendLine(sb, "updated", key, stringValueOf(file2Data.get(key)), stringValueOf(file1Data.get(key)));
        } else {
            throw new Exception("unknown status");
        }
    }

    private static void appendLine(StringBuilder sb, String status, String key, String curValue, String prevValue) {
        sb.append("Property '")
                .append(key)
                .append("' was ")
                .append(status);
        if (status.equals("added")) {
            sb.append(" with value: ")
                    .append(curValue);
        } else if (status.equals("updated")) {
            sb.append(". From ")
                    .append(prevValue)
                    .append(" to ")
                    .append(curValue);
        }
        sb.append('\n');
    }

    private static String stringValueOf(Object value) {
        if (value instanceof ArrayList || value instanceof HashMap) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "\'" + value + "\'";
        }
        return String.valueOf(value);
    }
}
