package com.github.marwin1991.keep_changelog.test;

import com.github.marwin1991.keep_changelog.generator.ChangelogGenerator;
import com.github.marwin1991.keep_changelog.model.Changelog;
import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;

import java.io.File;
import java.time.OffsetDateTime;

import static com.github.marwin1991.keep_changelog.yaml.parse.ChangelogEntryParser.getChangelogEntriesFromFile;

public class Test {

    public static void main(String[] args) {
        ChangelogEntry entry = getChangelogEntriesFromFile(new File("./changelog/unreleased/test-task.yml"));

        Changelog changelog = Changelog.builder()
                .version(ChangelogVersion.builder()
                        .version("1.0.0")
                        .releaseDateTime(OffsetDateTime.now())
                        .entry(entry)
                        .build())
                .build();

        ChangelogGenerator changelogGenerator = new ChangelogGenerator();
        System.out.println(changelogGenerator.generateChangelog(changelog));
    }
}
