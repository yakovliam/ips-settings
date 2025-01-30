package com.tarigma.ipssettings;

import com.beust.jcommander.JCommander;
import com.tarigma.ipssettings.detector.InputTypeDetector;
import com.tarigma.ipssettings.detector.InputTypeRelation;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.parser.RSEIParser;
import com.tarigma.ipssettings.parser.abb.ABBParser;
import com.tarigma.ipssettings.parser.ge.GEParser;
import com.tarigma.ipssettings.parser.sel.SELParser;
import com.tarigma.ipssettings.parser.siemens.SiemensParser;
import com.tarigma.ipssettings.writer.csv.RSEIContainerCSVWriter;
import com.tarigma.ipssettings.writer.xml.RSEIContainerXMLWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IpsSettingsBootstrapper implements ApplicationListener<ApplicationReadyEvent> {

  private static final Logger LOG = LoggerFactory.getLogger(IpsSettingsBootstrapper.class);

  @Value("#{ T(java.nio.file.Path).of('${gem.ips-settings.input-dir}') }")
  private Path inputDir;

  @Value("${gem.ips-settings.input-filename-suffix}")
  private String inputFilenameSuffix;

  @Value("#{ T(java.nio.file.Path).of('${gem.ips-settings.output-dir}') }")
  private Path outputDir;

  /**
   * Bootstrapper / entry point for the program
   *
   * @param event event
   */
  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    IpsSettingsArgs jct = new IpsSettingsArgs();
    String[] argv = event.getArgs();

    if (List.of(argv).isEmpty()) {
      // attempt conversion for each file in input directory
      try {
        Files.walk(inputDir).filter(Files::isRegularFile)
            .filter(path -> path.toString().endsWith(inputFilenameSuffix)).forEach(this::convert);

      } catch (Exception e) {
        LOG.error("failed to walk input directory", e);
      }
    }

    new JCommander(jct).parse(argv);

    try {
      Set<Path> files = Files.walk(inputDir).filter(Files::isRegularFile)
          .filter(path -> path.toString().endsWith(inputFilenameSuffix)).peek(System.out::println)
          .peek(p -> {
            System.out.println("p.getFileName() = " + p.getFileName());
          }).filter(file -> file.getFileName().toString().equals(jct.file()))
          .peek(System.out::println).collect(Collectors.toSet());

      LOG.info("Found {} targeted files to convert.", files.size());
      files.forEach(p -> convertWithInputTypeRelation(p, jct.man()));

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void convert(Path input) {
    List<String> inputData = null;
    try {
      inputData = Files.readAllLines(input);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    // detect type
    InputTypeRelation inputTypeRelation = InputTypeDetector.findInputType(inputData);

    if (inputTypeRelation == null) {
      LOG.error("failed to detect input type for file: {}", input);
    }
  }

  private void convertWithInputTypeRelation(Path input, InputTypeRelation inputTypeRelation) {
    LOG.info("converting input file: {}", input.getFileName());
    try {
      List<String> inputData = Files.readAllLines(input);

      // get corresponding parser
      RSEIParser parser;

      switch (Objects.requireNonNull(inputTypeRelation)) {
        case GE:
          parser = new GEParser();
          break;
        case SEL:
          parser = new SELParser();
          break;
        case SIEMENS:
          parser = new SiemensParser();
          break;
        case ABB:
          parser = new ABBParser();
          break;
        default:
          throw new IllegalStateException("Unexpected value: " + inputTypeRelation);
      }

      // parse into container
      RSEIContainer container = parser.parse(inputData);

      // convert container into XML
      String xmlContent = new RSEIContainerXMLWriter().write(container);

      Path outputDestDir = outputDir.resolve(inputDir.relativize(input.getParent()));
      Files.createDirectories(outputDestDir);

      // write XML to output in same folder structure as input
      String outputFileName = input.getFileName().toString().concat(".rsei.xml");
      Files.writeString(outputDestDir.resolve(outputFileName), xmlContent);
      LOG.info("    wrote output file: {}", outputFileName);

      // convert container into CSV
      String csvContent = new RSEIContainerCSVWriter().write(container);

      // write CSV to output in same folder structure as input
      String csvOutputFileName = input.getFileName().toString().concat(".csv");
      Files.writeString(outputDestDir.resolve(csvOutputFileName), csvContent);
      LOG.info("    wrote output file: {}", csvOutputFileName);
    } catch (Exception e) {
      LOG.error("failed to convert: {}", input, e);
    }
  }
}
