package com.github.marwin1991.keep_changelog.markdown.entry;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogLink;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownChangelogEntryLinksTest {

    @Test
    void getLinks_whenGivenNotEmptyListOfLinks_thenReturnFormattedString() {
        // given
        List<ChangelogLink> links = Stream.of(new ChangelogLink("google", "google.com"), new ChangelogLink("wp", "wp.pl"), new ChangelogLink("onet", "onet.pl")).collect(Collectors.toList());
        ChangelogEntry entry = ChangelogEntry.builder().links(links).build();
        MarkdownChangelogEntryLinks markdownChangelogEntryLinks = new MarkdownChangelogEntryLinks(entry);
        // when
        String expected = "[google](google.com) [wp](wp.pl) [onet](onet.pl)";
        String result = markdownChangelogEntryLinks.getLinks();
        // then
        assertEquals(expected, result);
    }

    @Test
    void getLinks_whenGivenNullListOfLinks_thenReturnEmptyString() {
        // given
        List<ChangelogLink> links = null;
        ChangelogEntry entry = ChangelogEntry.builder().links(links).build();
        MarkdownChangelogEntryLinks markdownChangelogEntryLinks = new MarkdownChangelogEntryLinks(entry);
        // when
        String result = markdownChangelogEntryLinks.getLinks();
        // then
        assertEquals(StringUtils.EMPTY, result);
    }
}
