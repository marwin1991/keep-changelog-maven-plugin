package com.github.marwin1991.keep_changelog.markdown.entry;

import com.github.marwin1991.keep_changelog.yaml.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownChangelogEntryTest {

    @Test
    void testToString() {
        // given
        ChangelogEntry.ChangelogEntryBuilder changelogEntryBuilder = ChangelogEntry.builder();
        List<Author> authors = new ArrayList<>(Arrays.asList(new Author("Piotr", "Peter", "github.com/marwin1991"), new Author("Lukasz", "Luq", "github.com/luqkrzy")));
        List<String> issues = Stream.of("issue1", "issue2", "issue3").collect(Collectors.toList());
        List<String> important = Stream.of("Important one", "Important two").collect(Collectors.toList());
        List<ChangelogLink> changeLinks = Stream.of(new ChangelogLink("google", "google.com"), new ChangelogLink("wp", "wp.pl"), new ChangelogLink("onet", "onet.pl")).collect(Collectors.toList());
        List<Configuration> config = Stream.of(new Configuration("conf type", ConfigurationAction.ADD, "key", "def value", "some description", "more info here")).collect(Collectors.toList());
        ChangelogEntry changelogEntry = changelogEntryBuilder.title("New Release")
                .authors(authors)
                .mergeRequest("Merge Request")
                .issues(issues)
                .links(changeLinks)
                .type(ChangeLogEntryType.ADDED)
                .importantNotes(important)
                .configuration(config)
                .build();

        // when
        String expected = "- New Release !Merge Request #issue1 #issue2 #issue3 [google](google.com) [wp](wp.pl) [onet](onet.pl) ([Piotr](github.com/marwin1991) @Peter) ([Lukasz](github.com/luqkrzy) @Luq)";
        MarkdownChangelogEntry markdownChangelogEntry = new MarkdownChangelogEntry(changelogEntry);
        String result = markdownChangelogEntry.toMarkdown();
        // then
        assertEquals(expected, result);
    }
}
