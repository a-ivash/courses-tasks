package com.ivashchenko.practice5.task55;

import java.io.*;

public class CommentCleaner {
    /** Reading file line by line and appending them to StringBuilder instance. */
    private static void readFile(File input, StringBuilder stringBuilder) {
        String s;
        try (BufferedReader inputStream = new BufferedReader(new FileReader(input))) {

            while ((s = inputStream.readLine()) != null) {
                stringBuilder.append(s + "\n");
            }
        } catch (IOException e) {        }
    }

    private static void writeFile(File output, StringBuilder stringBuilder) {
        try ( BufferedWriter outputWriter = new BufferedWriter(new FileWriter(output)) ){
            outputWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void clean(File input, File output){
        StringBuilder stringBuilder = new StringBuilder();
        readFile(input, stringBuilder);

        String original = stringBuilder.toString();

        cleanComments(stringBuilder);

        // if was modified
        if (!original.equals(stringBuilder.toString())) {
            writeFile(output, stringBuilder);
        }
    }

    private static void cleanComments(StringBuilder stringBuilder) {
        cleanOneLineComments(stringBuilder);
        cleanMultilineComments(stringBuilder);
    }

    private static void cleanOneLineComments(StringBuilder stringBuilder) {
        String data = stringBuilder.toString();
        data = data.replaceAll("//.+\\n", "\n");
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.append(data);
    }

    private static void cleanMultilineComments(StringBuilder stringBuilder) {
        String data = stringBuilder.toString();
        data = data.replaceAll("/\\*(.|\\n)*?(\\*/)", "");
        stringBuilder.delete(0, stringBuilder.length());
        stringBuilder.append(data);
    }
}
