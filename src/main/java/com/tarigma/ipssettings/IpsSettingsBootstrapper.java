package com.tarigma.ipssettings;

import com.tarigma.ipssettings.io.FileInputFinderService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

        // get input file
        try {
            // TODO this is a test
            System.out.println("fileInputFinderService.getIpsSettingsInput().getAbsolutePath() = " + fileInputFinderService.getIpsSettingsInput().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
