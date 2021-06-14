package com.github.marwin1991.keep_changelog.markdown.entry;

import com.github.marwin1991.keep_changelog.yaml.model.Author;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import net.steppschuh.markdowngenerator.link.Link;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class MarkdownChangelogEntryAuthor extends MarkdownChangelogEntryAbstract {

    private static final String authorFormat = "(${author})";
    private static final String AT = "@";

    public MarkdownChangelogEntryAuthor(ChangelogEntry entry) {
        super(entry);
    }

    public String getAuthors() {
        if (entry.getAuthors() == null || entry.getAuthors().size() == 0) {
            return StringUtils.EMPTY;
        }

        StringBuilder authors = new StringBuilder(StringUtils.EMPTY);
        for (Author author : entry.getAuthors()) {
            Map<String, String> valuesMap = new HashMap<>();
            valuesMap.put("author", getAuthor(author));
            StringSubstitutor sub = new StringSubstitutor(valuesMap);
            authors.append(sub.replace(authorFormat)).append(" ");
        }

        return authors.toString().trim();
    }

    private String getAuthor(Author author) {
        return (getAuthorName(author) + " " + getAuthorNick(author)).trim();
    }

    private String getAuthorName(Author author) {
        if (author == null) {
            return StringUtils.EMPTY;
        }

        if (StringUtils.isNotBlank(author.getUrl()) && StringUtils.isNotBlank(author.getName())) {
            return new Link(author.getName(), author.getUrl()).toString();
        }

        return StringUtils.defaultString(author.getName());
    }

    private String getAuthorNick(Author author) {
        if (StringUtils.isBlank(author.getNick())) {
            return StringUtils.EMPTY;
        }

        return AT + author.getNick();
    }
}
