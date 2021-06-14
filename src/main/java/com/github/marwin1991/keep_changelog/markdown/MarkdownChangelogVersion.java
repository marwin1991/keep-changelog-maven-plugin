package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.markdown.entry.MarkdownChangeLogEntryType;
import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import com.github.marwin1991.keep_changelog.yaml.model.ChangeLogEntryType;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import net.steppschuh.markdowngenerator.list.UnorderedList;
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

    @Override
    public String toString() {
        return toMarkdown();
    }

    private String getVersion() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getVersionHeading()).append("\n").append("\n");
        stringBuilder.append(getImportantNotes());

        for (ChangeLogEntryType type : ChangeLogEntryType.values()) {
            stringBuilder.append(new MarkdownChangeLogEntryType(type, changelogVersion.getEntries()));
        }
        stringBuilder.append(new MarkdownChangelogVersionConfiguration(changelogVersion));
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

    private String getImportantNotes() {
        List<String> importantNotes = new LinkedList<>();

        for (ChangelogEntry entry : changelogVersion.getEntries()) {
            if (entry.getImportantNotes() != null) {
                importantNotes.addAll(entry.getImportantNotes());
            }
        }

        if (importantNotes.size() != 0) {
            return new Heading("Important notes", 3) + "\n\n" + new UnorderedList<>(importantNotes) + "\n\n";
        } else {
            return StringUtils.EMPTY;
        }
    }
}
