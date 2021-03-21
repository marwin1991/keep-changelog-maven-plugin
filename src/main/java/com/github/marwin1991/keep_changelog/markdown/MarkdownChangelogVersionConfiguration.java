package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.model.Configuration;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.code.Code;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
        List<Configuration> configurations = new LinkedList<>();

        for (ChangelogEntry entry : changelogVersion.getEntries()) {
            configurations.addAll(entry.getConfiguration());
        }

        Collections.sort(configurations);

        if (configurations.size() != 0) {
            return new Heading("Configuration changes", 3) + "\n\n" + getConfigurationTable(configurations) + "\n";
        } else {
            return StringUtils.EMPTY;
        }
    }

    private Table getConfigurationTable(List<Configuration> configurations) {
        Table.Builder tableBuilder = new Table.Builder()
                .addRow("Type", "Action", "Key", "Default Value", "Description");

        for (Configuration configuration : configurations) {
            tableBuilder.addRow(
                    configuration.getType(),
                    configuration.getAction().getAction(),
                    new Code(configuration.getKey()),
                    new Code(configuration.getDefaultValue()),
                    configuration.getDescription());
        }

        return tableBuilder.build();
    }
}
