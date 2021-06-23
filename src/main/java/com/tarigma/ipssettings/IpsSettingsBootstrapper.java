package com.tarigma.ipssettings;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.tarigma.ipssettings.detector.InputTypeDetector;
import com.tarigma.ipssettings.detector.InputTypeRelation;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.ge.GEParser;
import com.tarigma.ipssettings.parser.sel.SELParser;
import com.tarigma.ipssettings.xml.RSEIContainerXMLWriter;

@Component
public class IpsSettingsBootstrapper implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(IpsSettingsBootstrapper.class);

    /**
     * Bootstrapper / entry point for the program
     *
     * @param event event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // attempt conversion for each file in input directory
        try {
            Path inputDirectory = Path.of(System.getProperty("user.dir"), "assets", "input");
            Files.walk(inputDirectory)
                    .filter(Files::isRegularFile)
                    .forEach(this::convert);

        } catch (Exception e) {
            LOG.error("failed to walk input directory", e);
        }
    }

    private void convert(Path input) {
        try {
            List<String> inputData = Files.readAllLines(input);

            // detect type
            InputTypeRelation inputTypeRelation = InputTypeDetector.findInputType(inputData);

            // get corresponding parser
            RSEIParser parser = inputTypeRelation == InputTypeRelation.GE ? new GEParser() : new SELParser();

            // parse into container
            RSEIContainer container = parser.parse(inputData);

            // convert container into XML
            String xmlContent = new RSEIContainerXMLWriter().write(container);

            // write to output
            String outputFileName = input.getFileName().toString().concat(".output.xml");
            Path outputDir = Path.of(System.getProperty("user.dir"), "assets", "output");
            Files.createDirectories(outputDir);
            Files.writeString(outputDir.resolve(outputFileName), xmlContent);
        } catch (Exception e) {
            LOG.error("failed to convert: {}", input, e);
        }
    }
}
