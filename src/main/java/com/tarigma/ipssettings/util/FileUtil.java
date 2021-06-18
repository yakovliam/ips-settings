package com.tarigma.ipssettings.util;

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
}
