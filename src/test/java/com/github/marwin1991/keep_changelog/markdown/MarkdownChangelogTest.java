package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.model.Changelog;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MarkdownChangelogTest {

    @Test
    void disableFormattingAndInspectionTest() {
        //given:
        Changelog changelog = new Changelog();
        String expectedFirstLine = "<!-- @formatter:off --><!-- noinspection -->";
        MarkdownChangelog markdownChangelog = new MarkdownChangelog(changelog);

        //when
        String output = markdownChangelog.toMarkdown();

        //then:
        assertTrue(output.startsWith(expectedFirstLine));
    }

}