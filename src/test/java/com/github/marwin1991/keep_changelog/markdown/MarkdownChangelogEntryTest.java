package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.markdown.entry.MarkdownChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.model.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownChangelogEntryTest {

    @Test
    void testToString() {
        // given
        ChangelogEntry.ChangelogEntryBuilder changelogEntryBuilder = ChangelogEntry.builder();
        ChangelogEntry changelogEntry = changelogEntryBuilder.title("New Release")
                .authors(List.of(new Author("Piotr", "Peter", "github.com/marwin1991"), new Author("Lukasz", "Luq", "github.com/luqkrzy")))
                .mergeRequest("Merge Request")
                .issues(List.of("issue1", "issue2", "issue3"))
                .links(List.of(new ChangelogLink("google", "google.com"), new ChangelogLink("wp", "wp.pl")))
                .type(ChangeLogEntryType.ADDED)
                .importantNotes(List.of("Important one", "Important two"))
                .configuration(List.of(new Configuration("conf type", ConfigurationAction.ADD, "key", "def value", "some description", "more info here")))
                .build();

        // when
        String expected = "- New Release !Merge Request #issue1 #issue2 #issue3 [google](google.com) [wp](wp.pl) ([Piotr](github.com/marwin1991) @Peter) ([Lukasz](github.com/luqkrzy) @Luq)";
        MarkdownChangelogEntry markdownChangelogEntry = new MarkdownChangelogEntry(changelogEntry);
        String result = markdownChangelogEntry.toMarkdown();
        // then
        assertEquals(expected, result);
    }
}
