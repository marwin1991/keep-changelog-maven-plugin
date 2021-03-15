package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import com.github.marwin1991.keep_changelog.yaml.model.ChangeLogEntryType;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.model.Configuration;
import net.steppschuh.markdowngenerator.list.UnorderedList;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.code.Code;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class MarkdownChangelogVersion implements Markdown {

    private static final String versionHeaderFormat = "[${version}]${releaseData}";
    private static final String releaseDatePrefix = " - ";
    public static final String releaseDateFormat = "yyyy-MM-dd";

    private final ChangelogVersion changelogVersion;

    public MarkdownChangelogVersion(ChangelogVersion changelogVersion) {
        this.changelogVersion = changelogVersion;
    }

    @Override
    public String toMarkdown() {
        return getVersion();
    }

    private String getVersion() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getVersionHeading()).append("\n").append("\n");
        stringBuilder.append(getRecommendations());

        for (ChangeLogEntryType type : ChangeLogEntryType.values()) {
            stringBuilder.append(new MarkdownChangeLogEntryType(type, changelogVersion.getEntries()).toMarkdown());
        }
        stringBuilder.append(getConfiguration());
        return stringBuilder.toString();
    }


    private Heading getVersionHeading() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("version", changelogVersion.getVersion());
        valuesMap.put("releaseData", getVersionDate());

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return new Heading(sub.replace(versionHeaderFormat), 2);
    }

    private String getVersionDate() {
        if (changelogVersion.getReleaseDateTime() == null) {
            return StringUtils.EMPTY;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(releaseDateFormat);
        return releaseDatePrefix + formatter.format(changelogVersion.getReleaseDateTime());
    }

    private String getRecommendations() {
        List<String> recommendations = new LinkedList<>();

        for (ChangelogEntry entry : changelogVersion.getEntries()) {
            recommendations.addAll(entry.getRecommendations());
        }

        if (recommendations.size() != 0) {
            return new Heading("Recommendations", 3) + "\n\n" + new UnorderedList<>(recommendations) + "\n\n";
        } else {
            return StringUtils.EMPTY;
        }
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
