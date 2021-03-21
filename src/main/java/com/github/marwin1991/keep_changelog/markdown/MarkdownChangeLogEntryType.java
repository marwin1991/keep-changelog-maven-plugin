package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.yaml.model.ChangeLogEntryType;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import net.steppschuh.markdowngenerator.text.heading.Heading;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MarkdownChangeLogEntryType implements Markdown {

    private final ChangeLogEntryType type;
    private final List<ChangelogEntry> entries;

    public MarkdownChangeLogEntryType(ChangeLogEntryType type, List<ChangelogEntry> entries) {
        this.type = type;
        this.entries = getEntriesForType(entries, type);
    }

    @Override
    public String toMarkdown() {
        return getType();
    }

    @Override
    public String toString() {
        return toMarkdown();
    }


    private List<ChangelogEntry> getEntriesForType(List<ChangelogEntry> entries, ChangeLogEntryType type) {
        return entries.stream()
                .filter(changelogEntry -> type.equals(changelogEntry.getType()))
                .collect(Collectors.toList());
    }

    private String getType() {
        if (entries == null || entries.size() == 0) {
            return StringUtils.EMPTY;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getTypeHeading()).append("\n").append("\n");

        for (ChangelogEntry entry : entries) {
            stringBuilder.append(new MarkdownChangelogEntry(entry)).append("\n");
        }

        return stringBuilder.append("\n").toString();
    }

    private Heading getTypeHeading() {
        return new Heading(StringUtils.capitalize(type.getType()) + " " + getChangesNumber(entries), 3);
    }

    private String getChangesNumber(List<ChangelogEntry> entriesByType) {
        if (entriesByType.size() == 1) {
            return "(1 change)";
        } else {
            return "(" + entriesByType.size() + " changes)";
        }
    }


}
