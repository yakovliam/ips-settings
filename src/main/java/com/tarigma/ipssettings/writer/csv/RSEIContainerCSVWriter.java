package com.tarigma.ipssettings.writer.csv;

import com.tarigma.ipssettings.model.Block;
import com.tarigma.ipssettings.model.RSEIContainer;
import com.tarigma.ipssettings.model.parameter.Parameter;

import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.tarigma.ipssettings.writer.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Write a CSV representation of a ParameterSet in the format:
 * <p>
 * {@code [<BLOCK_NAME>].[<PARAM_NAME>],"<VALUE>"}
 * <p>
 * For newer SEL devices, instead use the format:
 * <p>
 * {@code [<BLOCK_DESCRIPTION>].[<PARAM_NAME>],"<VALUE>"}
 * <p>
 * If a Parameter does not belong to a Block, skip it.
 */
public class RSEIContainerCSVWriter implements Writer<RSEIContainer, String> {

    private static final Logger LOG = LoggerFactory.getLogger(RSEIContainerCSVWriter.class);

    private static final Function<Block, String> nameGetter = Block::getName;

    private static final Function<Block, String> descriptionGetter = Block::getDescription;

    @Override
    public String write(RSEIContainer container) {
        // TODO - make detection less hacky
        if (container.getRelayInfo().getRelayType().contains("421")) {
            // write with block descriptions
            return write(container, descriptionGetter);
        } else {
            // write with block names
            return write(container, nameGetter);
        }
    }

    private String write(RSEIContainer container, Function<Block, String> getter) {
        Map<UUID, Block> blocksById = container.getBlocks().stream()
                .collect(Collectors.toMap(Block::getBlockID, Function.identity()));

        StringBuilder result = new StringBuilder();
        for (Parameter<?> param : container.getParameterSet().getParameterSet()) {
            try {
                StringBuilder line = new StringBuilder();
                line.append('[').append(getter.apply(blocksById.get(param.getBlockId()))).append(']');
                line.append('.');
                line.append('[').append(param.getName()).append(']');
                line.append(',');
                line.append('"');

                String value = param.getValue();
                if (value != null) {
                    line.append(value);
                }

                line.append('"');
                line.append('\n');

                result.append(line);
            } catch (RuntimeException e) {
                LOG.debug("    no block found for parameter '{}'", param.getName());
            }
        }

        return result.toString();
    }
}
