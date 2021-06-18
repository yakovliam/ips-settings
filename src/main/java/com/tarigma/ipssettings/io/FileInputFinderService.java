package com.tarigma.ipssettings.io;

import com.tarigma.ipssettings.model.InputFileExtension;
import com.tarigma.ipssettings.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileInputFinderService {

    /**
     * Returns the input file to parse
     *
     * @return the input file
     */
    public File getIpsSettingsInput() throws IOException {
        File inputDirectory = new File(System.getProperty("user.dir") + File.separator + "assets", "input");

        return Files.list(Paths.get(inputDirectory.toURI()))
                .map(Path::toFile)
                .filter(file -> FileUtil.getFileExtension(file.getPath()).equalsIgnoreCase(InputFileExtension.SET.getExt()))
                .findFirst()
                .orElse(null);
    }
}
