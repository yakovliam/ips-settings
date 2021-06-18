package com.tarigma.ipssettings.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtil {

    /**
     * Returns the extension of a file given the entire path (or just file name)
     * <p>
     * Example: {@code /home/username/Desktop/testTextFile.txt} -> returns -> {@code txt}
     *
     * @param path path
     * @return extension
     */
    public static String getFileExtension(String path) {
        String extension = "";

        int i = path.lastIndexOf('.');
        if (i >= 0) {
            extension = path.substring(i + 1);
        }
        return extension;
    }

    /**
     * Reads the contents of a file into a list of strings, where each element represents a line
     *
     * @param file file
     * @return lines
     */
    public static List<String> readContents(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        List<String> lines = new ArrayList<String>();

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        return lines;
    }
}
