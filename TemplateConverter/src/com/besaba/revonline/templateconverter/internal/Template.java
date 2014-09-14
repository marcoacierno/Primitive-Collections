package com.besaba.revonline.templateconverter.internal;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe ti permette di formattare una stringa X
 * e di sostituire dei blocchi con valori di variabili.
 *
 * @author Marco Acierno
 */
public class Template {
    private final Pattern pattern;

    public Template(String start, String end) {
        pattern = Pattern.compile(Pattern.quote(start) + "([^}]+)" + Pattern.quote(end));
    }

    public Template() {
        this("{", "}");
    }

    public String format(String text, Map<String, String> variables) {
        Matcher matcher = pattern.matcher(text);
       /* appendReplacement e appendTail accettano solo StringBuffer */
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = variables.get(key);

            if (value == null) {
                matcher.appendReplacement(buffer, "{" + key + "}");
                continue;
            }

            matcher.appendReplacement(buffer, value);
        }

        matcher.appendTail(buffer);
        return buffer.toString();
    }
}
