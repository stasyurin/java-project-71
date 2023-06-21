package hexlet.code.formatters;

import java.util.Map;
import java.util.SortedMap;

public class Stylish {

    public static String buildString(SortedMap<String, Map> diff) throws Exception {
        if (diff.isEmpty()) {
            return "{}";
        }

        var sb = new StringBuilder();
        sb.append("{\n");
        for (var keyDiff : diff.entrySet()) {
            appendBlock(sb, keyDiff);
        }
        sb.append("}");

        return sb.toString();
    }

    private static void appendBlock(StringBuilder sb, Map.Entry<String, Map> keyDiff) throws Exception {
        var key = keyDiff.getKey();
        var diff = keyDiff.getValue();
        var status = diff.get("status");
        if (status.equals("unchanged")) {
            appendLine(sb, ' ', key, String.valueOf(diff.get("value")));
        } else if (status.equals("added")) {
            appendLine(sb, '+', key, String.valueOf(diff.get("value")));
        } else if (status.equals("deleted")) {
            appendLine(sb, '-', key, String.valueOf(diff.get("value")));
        } else if (status.equals("changed")) {
            appendLine(sb, '-', key, String.valueOf(diff.get("previous value")));
            appendLine(sb, '+', key, String.valueOf(diff.get("current value")));
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
