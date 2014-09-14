package com.besaba.revonline.templateconverter;

import com.besaba.revonline.templateconverter.internal.Template;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marco
 * @since 1.0
 */
public class FileByFileMain {
    // D:\Users\ReVo\Documents\IntelliJ IDEA\PrimitiveCollections\templates\List\ArrayList.txt
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Posizione template");

        String templatePosition = reader.readLine();
        File file = new File(templatePosition);

        if (!file.exists() || !file.isFile()) {
            System.out.println(templatePosition + " is not valid.");
            return;
        }

        System.out.println("Quale tipo devo inserire?");
        String type = reader.readLine();

        String fileName = file.getName();

        final int extensionDot = fileName.lastIndexOf('.');
        fileName = fileName.substring(0, extensionDot != -1 ? extensionDot : fileName.length());

        final String upperCaseType = Character.toUpperCase(type.charAt(0)) + type.substring(1);
        final String className = upperCaseType + fileName;
        String outputFileString = file.getParent() + "\\" + className + ".java";

        System.out.println("OK: " + outputFileString);

        Map<String, String> variabili = new HashMap<>();

        variabili.put("type", type);
        variabili.put("className", className);
        variabili.put("iterable", upperCaseType + "Iterable");
        variabili.put("iterator", upperCaseType + "Iterator");
        variabili.put("consumer", upperCaseType + "Consumer");
        variabili.put("upperCaseType", upperCaseType);

        String newVariable;

        do {
            System.out.println("Variabili attualmente assegnate (usa \"no\" per avviare la conversione):");

            for (Map.Entry<String, String> entry : variabili.entrySet()) {
                System.out.println("Nome => " + entry.getKey() + " Valore => " + entry.getValue());
            }

            newVariable = reader.readLine();

            if ("no".equals(newVariable)) {
                break;
            }

            System.out.println("Quale valore vuoi assegnare alla variabile " + newVariable + "?");
            String value = reader.readLine();

            variabili.put(newVariable, value);
        } while ("no".equals(newVariable));

        Template template = new Template("{{", "}}");
        Path outputFile = Paths.get(outputFileString);

        try (BufferedReader bufferedReader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8);
            BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardCharsets.UTF_8)) {
            for (String line; (line = bufferedReader.readLine()) != null; ) {
                String output = template.format(line, variabili);
                writer.append(output).append(System.lineSeparator());
            }
        }
    }
}
