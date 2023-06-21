package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

public class Plain {
    public static String buildString(SortedMap<String, Map> diff) throws Exception {
        if (diff.isEmpty()) {
            return "";
        }

        var sb = new StringBuilder();
        for (var keyDiff : diff.entrySet()) {
            appendBlock(sb, keyDiff);
        }

        return sb.toString();
    }

    private static void appendBlock(StringBuilder sb, Map.Entry<String, Map> keyDiff) throws Exception {
        var key = keyDiff.getKey();
        var diff = keyDiff.getValue();
        var status = diff.get("status");
        if (status.equals("unchanged")) {
            return;
        } else if (status.equals("added")) {
            appendLine(sb, "added", key, stringValueOf(diff.get("value")), stringValueOf(null));
        } else if (status.equals("deleted")) {
            appendLine(sb, "removed", key, stringValueOf(null), stringValueOf(null));
        } else if (status.equals("changed")) {
            appendLine(sb, "updated", key, stringValueOf(diff.get("current value")),
                    stringValueOf(diff.get("previous value")));
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
