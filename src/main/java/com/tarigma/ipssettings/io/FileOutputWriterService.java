package com.tarigma.ipssettings.io;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileOutputWriterService {

    /**
     * Writes the given output text to the output XML file
     *
     * @param output output
     */
    public void writeToOutputFile(String output) throws IOException {
        File outputFile = new File(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "output", "output.xml");

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile, true));
        bufferedWriter.append(output);

        // done!
        bufferedWriter.close();
    }
}
