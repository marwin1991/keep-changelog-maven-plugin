package com.github.marwin1991.keep_changelog.markdown.entry;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownChangelogEntryMergeRequestTest {

    @Test
    void getMergeRequest_whenGivenNotEmptyString_thenReturnFormattedString() {
        // given
        String str = "some request";
        ChangelogEntry entry = ChangelogEntry.builder().mergeRequest(str).build();
        MarkdownChangelogEntryMergeRequest markdownChangelogEntryMergeRequest = new MarkdownChangelogEntryMergeRequest(entry);
        // when
        String expected = "!" + str;
        String result = markdownChangelogEntryMergeRequest.getMergeRequest();
        // then
        assertEquals(expected, result);
    }

    @Test
    void getMergeRequest_whenGivenNull_thenReturnEmptyString() {
        // given
        String str = null;
        ChangelogEntry entry = ChangelogEntry.builder().mergeRequest(null).build();
        MarkdownChangelogEntryMergeRequest markdownChangelogEntryMergeRequest = new MarkdownChangelogEntryMergeRequest(entry);
        // when
        String result = markdownChangelogEntryMergeRequest.getMergeRequest();
        // then
        assertEquals(StringUtils.EMPTY, result);
    }
    @Test
    void getMergeRequest_whenGivenEmpty_thenReturnEmptyString() {
        // given
        String str = "";
        ChangelogEntry entry = ChangelogEntry.builder().mergeRequest(str).build();
        MarkdownChangelogEntryMergeRequest markdownChangelogEntryMergeRequest = new MarkdownChangelogEntryMergeRequest(entry);
        // when
        String result = markdownChangelogEntryMergeRequest.getMergeRequest();
        // then
        assertEquals(StringUtils.EMPTY, result);
    }
}
