package com.tarigma.ipssettings;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.tarigma.ipssettings.detector.InputTypeDetector;
import com.tarigma.ipssettings.detector.InputTypeRelation;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.ge.GEParser;
import com.tarigma.ipssettings.parser.sel.SELParser;
import com.tarigma.ipssettings.xml.RSEIContainerXMLWriter;

@Component
public class IpsSettingsBootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(IpsSettingsBootstrapper.class);

    @Value("#{ T(java.nio.file.Path).of('${gem.ips-settings.input-dir}') }")
    private Path inputDir;

    @Value("#{ T(java.nio.file.Path).of('${gem.ips-settings.output-dir}') }")
    private Path outputDir;

    /**
     * Bootstrapper / entry point for the program
     *
     * @param event event
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        // attempt conversion for each file in input directory
        try {
            Files.walk(inputDir)
                    .filter(Files::isRegularFile)
                    .forEach(this::convert);

        } catch (Exception e) {
            LOG.error("failed to walk input directory", e);
        }
    }

    private void convert(Path input) {
    	LOG.info("converting input file: {}", input.getFileName());
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

            // write to output in same folder structure as input
            String outputFileName = input.getFileName().toString().concat(".rsei.xml");
            Path outputDestDir = outputDir.resolve(inputDir.relativize(input.getParent()));
            Files.createDirectories(outputDestDir);
            Files.writeString(outputDestDir.resolve(outputFileName), xmlContent);
            LOG.info("    wrote output file: {}", outputFileName);
        } catch (Exception e) {
            LOG.error("failed to convert: {}", input, e);
        }
    }
}
