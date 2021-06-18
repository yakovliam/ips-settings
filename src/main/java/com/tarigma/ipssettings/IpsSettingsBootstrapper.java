package com.tarigma.ipssettings;

import com.tarigma.ipssettings.detector.InputTypeDetector;
import com.tarigma.ipssettings.detector.InputTypeRelation;
import com.tarigma.ipssettings.io.FileInputFinderService;
import com.tarigma.ipssettings.io.FileOutputWriterService;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.sel.SELParser;
import com.tarigma.ipssettings.parser.ge.GEParser;
import com.tarigma.ipssettings.util.FileUtil;
import com.tarigma.ipssettings.xml.RSEIContainerXMLWriter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class IpsSettingsBootstrapper implements ApplicationListener<ContextRefreshedEvent> {


    /**
     * Bootstrapper / entry point for the program
     *
     * @param event event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // get input service
        FileInputFinderService fileInputFinderService = event.getApplicationContext().getBean(FileInputFinderService.class);

        // do conversion
        try {

            // get input file
            File input = fileInputFinderService.getIpsSettingsInput();

            // get input data
            List<String> inputData = FileUtil.readContents(input);

            // detect type
            InputTypeRelation inputTypeRelation = InputTypeDetector.findInputType(inputData);

            // get corresponding parser
            RSEIParser parser = inputTypeRelation == InputTypeRelation.GE ? new GEParser() : new SELParser();

            // parse into container
            RSEIContainer container = parser.parse(inputData);

            // convert container into XML
            String xmlString = new RSEIContainerXMLWriter().write(container);

            // get output service
            FileOutputWriterService fileOutputWriterService = event.getApplicationContext().getBean(FileOutputWriterService.class);
            // write to output
            fileOutputWriterService.writeToOutputFile(xmlString);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
