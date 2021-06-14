package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.markdown.entry.MarkdownChangelogEntryIssues;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownChangelogEntryIssuesTest {

    @Test
    void getIssues_whenGivenNotEmptyListOfString_thenReturnFormattedString() {
        // given
        List<String> issues = Stream.of("issue1", "issue2", "issue3").collect(Collectors.toList());
        ChangelogEntry entry = ChangelogEntry.builder().issues(issues).build();
        MarkdownChangelogEntryIssues markdownChangelogEntryIssues = new MarkdownChangelogEntryIssues(entry);
        // when
        String expected = "#issue1 #issue2 #issue3";
        String result = markdownChangelogEntryIssues.getIssues();
        // then
        assertEquals(expected, result);
    }

    @Test
    @Disabled
    void getIssues_whenGivenEmptyListOfString_thenReturn() {
        // given
        List<String> issues = Stream.of("", "", "").collect(Collectors.toList());
        ChangelogEntry entry = ChangelogEntry.builder().issues(issues).build();
        MarkdownChangelogEntryIssues markdownChangelogEntryIssues = new MarkdownChangelogEntryIssues(entry);
        // when
        String expected = "#issue1 #issue2 #issue3";
        String result = markdownChangelogEntryIssues.getIssues();
        System.out.println(result);
        // then
        assertEquals(expected, result);
    }

    @Test
    void getIssues__whenEmptyListOfString_thenReturnEmptyString() {
        // given
        List<String> issues = null;
        ChangelogEntry entry = ChangelogEntry.builder().issues(issues).build();
        MarkdownChangelogEntryIssues markdownChangelogEntryIssues = new MarkdownChangelogEntryIssues(entry);
        // when
        String result = markdownChangelogEntryIssues.getIssues();
        // then
        assertEquals(StringUtils.EMPTY, result);
    }
}
