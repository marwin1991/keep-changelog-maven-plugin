package com.github.marwin1991.keep_changelog.generator;

import com.github.marwin1991.keep_changelog.model.Changelog;
import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import com.github.marwin1991.keep_changelog.yaml.model.ChangeLogEntryType;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import net.steppschuh.markdowngenerator.list.UnorderedListItem;
import net.steppschuh.markdowngenerator.text.Text;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ChangelogGenerator {

    public String generateChangelog(Changelog changelog) {
        StringBuilder stringBuilder = new StringBuilder()
                .append(generateHeader(changelog.getHeader())).append("\n");

        for (ChangelogVersion version : changelog.getVersions()) {
            stringBuilder.append(generateVersionHeading(version)).append("\n");
            stringBuilder = generateVersionBody(stringBuilder, version.getEntries()).append("\n");
        }
        return stringBuilder.toString();
    }

    private StringBuilder generateVersionBody(StringBuilder stringBuilder, List<ChangelogEntry> entries) {
        List<List<ChangelogEntry>> allEntriesByType = new LinkedList<>();

        for (ChangeLogEntryType changeLogEntryType : ChangeLogEntryType.values()) {
            allEntriesByType.add(getEntriesForType(entries, changeLogEntryType));
        }

        for (List<ChangelogEntry> entriesByType : allEntriesByType) {
            stringBuilder = generateType(stringBuilder, entriesByType);
        }

        //TODO recomendations
        //TODO configuration

        return stringBuilder;
    }

    private StringBuilder generateType(StringBuilder stringBuilder, List<ChangelogEntry> entriesByType) {
        if (entriesByType.size() != 0) {
            stringBuilder.append(generateTypeHeading(entriesByType)).append("\n");
            stringBuilder = generateTypeBody(stringBuilder, entriesByType);
        }

        return stringBuilder;
    }

    private StringBuilder generateTypeBody(StringBuilder stringBuilder, List<ChangelogEntry> entriesByType) {
        for (ChangelogEntry entry : entriesByType) {
            stringBuilder.append(generateEntry(entry)).append("\n");
        }

        return stringBuilder;
    }

    private UnorderedListItem generateEntry(ChangelogEntry entry) {
        return new UnorderedListItem(entry.getTitle());
    }

    private Heading generateTypeHeading(List<ChangelogEntry> entriesByType) {
        return new Heading(StringUtils.capitalize(entriesByType.get(0).getType().getType()) + " " + getChangesNumber(entriesByType), 3);
    }

    private String getChangesNumber(List<ChangelogEntry> entriesByType) {
        if (entriesByType.size() == 1) {
            return "(1 change)";
        } else {
            return "(" + entriesByType.size() + " changes)";
        }
    }

    private List<ChangelogEntry> getEntriesForType(List<ChangelogEntry> entries, ChangeLogEntryType type) {
        return entries.stream()
                .filter(changelogEntry -> type.equals(changelogEntry.getType()))
                .collect(Collectors.toList());
    }

    public Text generateHeader(String header) {
        return new Text(header);
    }

    public Heading generateVersionHeading(ChangelogVersion version) {
        return new Heading(version.getVersion() + generateVersionDate(version.getReleaseDateTime()), 2);
    }

    public String generateVersionDate(OffsetDateTime dateTime) {
        if (dateTime == null) {
            return StringUtils.EMPTY;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(dateTime);
    }
}
