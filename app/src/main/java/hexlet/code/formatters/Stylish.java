package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Stylish {

    public static String buildString(Map<String, Object> file1Data, Map<String, Object> file2Data,
                                     SortedMap<String, String> keyStatuses) throws Exception {
        if (keyStatuses.isEmpty()) {
            return "{}";
        }

        var sb = new StringBuilder();
        sb.append("{\n");
        for (var keyStatus : keyStatuses.entrySet()) {
            appendBlock(sb, keyStatus, file1Data, file2Data);
        }
        sb.append("}\n");

        return sb.toString();
    }

    private static void appendBlock(StringBuilder sb, Map.Entry<String, String> keyStatus,
                                    Map<String, Object> file1Data, Map<String, Object> file2Data) throws Exception {
        var key = keyStatus.getKey();
        var status = keyStatus.getValue();
        if (status.equals("unchanged")) {
            appendLine(sb, ' ', key, String.valueOf(file1Data.get(key)));
        } else if (status.equals("added")) {
            appendLine(sb, '+', key, String.valueOf(file2Data.get(key)));
        } else if (status.equals("deleted")) {
            appendLine(sb, '-', key, String.valueOf(file1Data.get(key)));
        } else if (status.equals("changed")) {
            appendLine(sb, '-', key, String.valueOf(file1Data.get(key)));
            appendLine(sb, '+', key, String.valueOf(file2Data.get(key)));
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
}
