package com.github.marwin1991.keep_changelog.test;

import com.github.marwin1991.keep_changelog.markdown.MarkdownChangelog;
import com.github.marwin1991.keep_changelog.model.Changelog;
import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;

import java.io.File;
import java.time.OffsetDateTime;

import static com.github.marwin1991.keep_changelog.yaml.parse.ChangelogEntryParser.getChangelogEntriesFromFile;

public class Test {

    public static void main(String[] args) {
        ChangelogEntry entry = getChangelogEntriesFromFile(new File("./changelog/unreleased/test-task.yml"));
        ChangelogEntry entry2 = getChangelogEntriesFromFile(new File("./changelog/unreleased/test-task2.yml"));
        ChangelogEntry entry3 = getChangelogEntriesFromFile(new File("./changelog/unreleased/test-task3.yml"));
        // ChangelogEntry entry4 = getChangelogEntriesFromFile(new File("./changelog/unreleased/test-task4.yml"));


        Changelog changelog = Changelog.builder()
                .version(ChangelogVersion.builder()
                        .version("1.0.2")
                        .releaseDateTime(OffsetDateTime.now())
                        .entry(entry)
                        .entry(entry2)
                        .entry(entry2)
                        .entry(entry3)
                        .entry(entry3)
                        // .entry(entry4)
                        .build())
                .version(ChangelogVersion.builder()
                        .version("1.0.1")
                        .releaseDateTime(OffsetDateTime.now())
                        .entry(entry)
                        .entry(entry)
                        .entry(entry)
                        .build())
                .version(ChangelogVersion.builder()
                        .version("1.0.0")
                        .releaseDateTime(OffsetDateTime.now())
                        .entry(entry)
                        .entry(entry)
                        .entry(entry)
                        .build())
                .build();

        MarkdownChangelog markdownChangelog = new MarkdownChangelog(changelog);
        System.out.println(markdownChangelog);
    }
}
