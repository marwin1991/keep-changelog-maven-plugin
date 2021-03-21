package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.model.Configuration;
import net.steppschuh.markdowngenerator.list.UnorderedListItem;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.code.Code;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class MarkdownChangelogVersionConfiguration implements Markdown {

    private final ChangelogVersion changelogVersion;

    public MarkdownChangelogVersionConfiguration(ChangelogVersion changelogVersion) {
        this.changelogVersion = changelogVersion;
    }

    @Override
    public String toMarkdown() {
        return getConfiguration();
    }

    @Override
    public String toString() {
        return toMarkdown();
    }

    private String getConfiguration() {
        List<Configuration> configurations = getConfigurations();
        Set<String> types = getTypes(configurations);

        if (configurations.size() != 0) {
            StringBuilder markdownConfiguration = new StringBuilder(new Heading("Configuration changes", 3) + "\n\n");

            for(String type: types) {
                markdownConfiguration.append(getConfigurationTable(configurations, type)).append("\n");
            }

            return markdownConfiguration.toString();
        } else {
            return StringUtils.EMPTY;
        }
    }

    private List<Configuration> getConfigurations() {
        List<Configuration> configurations = new LinkedList<>();

        for (ChangelogEntry entry : changelogVersion.getEntries()) {
            configurations.addAll(entry.getConfiguration());
        }

        Collections.sort(configurations);
        return configurations;
    }

    private Set<String> getTypes(List<Configuration> configurations) {
        return configurations.stream().map(Configuration::getType).collect(Collectors.toSet());
    }

    private Table getConfigurationTable(List<Configuration> configurations, String type) {
        Table.Builder tableBuilder = new Table.Builder()
                .addRow("Type: " + type);

        for (Configuration configuration : configurations) {
            tableBuilder.addRow(
                    new UnorderedListItem(configuration.getAction().getDisplayText() + new Code(configuration.getKey())).toString() +
                            new UnorderedListItem(        " with default value: " + new Code(configuration.getDefaultValue())).toString() +
                    configuration.getDescription());
        }

        return tableBuilder.build();
    }
}
