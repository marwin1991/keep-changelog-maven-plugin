package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.yaml.model.Author;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownChangelogEntryAuthorTest {

    @Test
    void getAuthors_whenGivenNotEmptyListOfAuthors_thenReturnFormattedString() {
        // given
        List<Author> authors = List.of(new Author("Piotr", "Peter", "github.com/marwin1991"), new Author("Lukasz", "Luq", "github.com/luqkrzy"));
        ChangelogEntry entry = ChangelogEntry.builder().authors(authors).build();
        MarkdownChangelogEntryAuthor markdownChangelogEntryAuthor = new MarkdownChangelogEntryAuthor(entry);
        // when
        String expected = "([Piotr](github.com/marwin1991) @Peter) ([Lukasz](github.com/luqkrzy) @Luq)";
        String result = markdownChangelogEntryAuthor.getAuthors();
        // then
        assertEquals(expected, result);
    }

    @Test
    void getAuthors_whenGivenNullListOfAuthors_thenReturnEmptyString() {
        // given
        List<Author> authors = null;
        ChangelogEntry entry = ChangelogEntry.builder().authors(authors).build();
        MarkdownChangelogEntryAuthor markdownChangelogEntryAuthor = new MarkdownChangelogEntryAuthor(entry);
        // when
        String result = markdownChangelogEntryAuthor.getAuthors();
        // then
        assertEquals(StringUtils.EMPTY, result);
    }
}
