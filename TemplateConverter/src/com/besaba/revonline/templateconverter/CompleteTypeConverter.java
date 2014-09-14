package com.besaba.revonline.templateconverter;

import com.besaba.revonline.templateconverter.internal.Template;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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
public class CompleteTypeConverter {
    public static void main(String[] args) throws IOException {
        // hello you
        // change the values with your values
        // the tool will convert the template for all primitive types
        final String templatesPosition = "D:\\Users\\ReVo\\Documents\\IntelliJ IDEA\\PrimitiveCollections\\templates";
        final String folder = "List";
        final String fileName = "ArrayList";
        final String iteratorsFolder = "Iterators";
        final String consumerFolder = "Consumer";
        // sources directories
        // yes, you can use relative paths yaaaay!
        final String
                sourceClassDir = "D:\\Users\\ReVo\\Documents\\IntelliJ IDEA\\PrimitiveCollections\\src\\com\\besaba\\revonline\\primitivecollections\\list\\arraylist";
        final String
                sourceClassIterable = "D:\\Users\\ReVo\\Documents\\IntelliJ IDEA\\PrimitiveCollections\\src\\com\\besaba\\revonline\\primitivecollections\\iterables";
        final String
                sourceClassIterator = "D:\\Users\\ReVo\\Documents\\IntelliJ IDEA\\PrimitiveCollections\\src\\com\\besaba\\revonline\\primitivecollections\\iterables\\iterators";
        final String
                sourceClassConsumer = "D:\\Users\\ReVo\\Documents\\IntelliJ IDEA\\PrimitiveCollections\\src\\com\\besaba\\revonline\\primitivecollections\\function\\";
        // put it to true if you are recreating everything
        final boolean overrideIfExists = false; //oh and for now it doesn't work (lol it's not implemented yet)
        // types which will be used
        final String[] typesToConvert = {"boolean", "byte", "float", "short", "double", "char"};

        // this map is used to convert every {{default}} to it
        final Map<String, String> typeAndDefault = new HashMap<>();

        typeAndDefault.put("boolean", "false");
        typeAndDefault.put("byte", "0");
        typeAndDefault.put("int", "0");
        typeAndDefault.put("short", "0");
        typeAndDefault.put("long", "0");
        typeAndDefault.put("float", "0.0f");
        typeAndDefault.put("double", "0.0d");
        typeAndDefault.put("char", "'\\\\u0000'");

        for (String type : typesToConvert) {
            System.out.println("Current type: " + type);
            String upperCaseType = Character.toUpperCase(type.charAt(0)) + type.substring(1);
            String className = upperCaseType + fileName;
            final String iteratorName = upperCaseType + "Iterator";
            final String iterableName = upperCaseType + "Iterable";
            final String consumerName = upperCaseType + "Consumer";

            Map<String, String> variabili = new HashMap<>();

            variabili.put("type", type);
            variabili.put("className", className);
            variabili.put("iterable", iterableName);
            variabili.put("iterator", iteratorName);
            variabili.put("consumer", upperCaseType + "Consumer");
            variabili.put("upperCaseType", upperCaseType);
            variabili.put("default", typeAndDefault.get(type));

            Path classFile = Paths.get(sourceClassDir, className + ".java");
            Path iteratorFile = Paths.get(sourceClassIterator, iteratorName + ".java");
            Path iterableFile = Paths.get(sourceClassIterable, iterableName + ".java");
            Path consumerFile = Paths.get(sourceClassConsumer, consumerName + ".java");

            Template template = new Template("{{", "}}");

            try (BufferedReader source = Files.newBufferedReader(Paths.get(templatesPosition, folder, fileName + ".txt"), StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(classFile, StandardCharsets.UTF_8)) {
                for (String line; (line = source.readLine()) != null; ) {
                    writer.append(template.format(line,variabili))
                            .append(System.lineSeparator());
                }
            }

            try (BufferedReader source = Files.newBufferedReader(Paths.get(templatesPosition, iteratorsFolder, "Iterator.txt"), StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(iteratorFile, StandardCharsets.UTF_8)) {
                for (String line; (line = source.readLine()) != null; ) {
                    writer.append(template.format(line,variabili))
                            .append(System.lineSeparator());
                }
            }

            try (BufferedReader source = Files.newBufferedReader(Paths.get(templatesPosition, iteratorsFolder, "Iterable.txt"), StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(iterableFile, StandardCharsets.UTF_8)) {
                for (String line; (line = source.readLine()) != null; ) {
                    writer.append(template.format(line,variabili))
                            .append(System.lineSeparator());
                }
            }

            try (BufferedReader source = Files.newBufferedReader(Paths.get(templatesPosition, consumerFolder, "Consumer.txt"), StandardCharsets.UTF_8);
                 BufferedWriter writer = Files.newBufferedWriter(consumerFile, StandardCharsets.UTF_8)) {
                for (String line; (line = source.readLine()) != null; ) {
                    writer.append(template.format(line,variabili))
                            .append(System.lineSeparator());
                }
            }
        }
    }
}
