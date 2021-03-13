package com.github.marwin1991.keep_changelog.generator;

import com.github.marwin1991.keep_changelog.yaml.model.Author;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogLink;
import net.steppschuh.markdowngenerator.MarkdownElement;
import net.steppschuh.markdowngenerator.link.Link;
import net.steppschuh.markdowngenerator.list.UnorderedListItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class MarkdownChangelogEntry {
    private static final String entryFormat = "${title} ${merge_request} ${issues} ${links} ${authors}";
    private static final String mergeRequestFormat = "!${merge_request}";
    private static final String issueFormat = "#${issue}";
    private static final String authorFormat = "(${author})";

    private final ChangelogEntry entry;

    public MarkdownChangelogEntry(ChangelogEntry entry) {
        this.entry = entry;
    }

    public MarkdownElement toMarkdown() {
        return new UnorderedListItem(getEntry());
    }

    private String getEntry() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("title", entry.getTitle());
        valuesMap.put("merge_request", getMergeRequest());
        valuesMap.put("issues", getIssues());
        valuesMap.put("links", getLinks());
        valuesMap.put("authors", getAuthors());

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(entryFormat);
    }

    private String getMergeRequest() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("merge_request", entry.getMergeRequest());
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(mergeRequestFormat);
    }

    private String getIssues() {
        if (entry.getIssues() == null || entry.getIssues().size() == 0) {
            return StringUtils.EMPTY;
        }

        if (entry.getIssues().size() == 1) {
            Map<String, String> valuesMap = new HashMap<>();
            valuesMap.put("issue", entry.getIssues().get(0));
            StringSubstitutor sub = new StringSubstitutor(valuesMap);
            return sub.replace(issueFormat);
        }

        StringBuilder issues = new StringBuilder(StringUtils.EMPTY);
        for (String issue : entry.getIssues()) {
            Map<String, String> valuesMap = new HashMap<>();
            valuesMap.put("issue", issue);
            StringSubstitutor sub = new StringSubstitutor(valuesMap);
            issues.append(sub.replace(issueFormat)).append(" ");
        }

        return issues.toString().trim();
    }

    private String getLinks() {
        if (entry.getLinks() == null || entry.getLinks().size() == 0) {
            return StringUtils.EMPTY;
        }

        if (entry.getLinks().size() == 1) {
            return new Link(entry.getLinks().get(0).getName(), entry.getLinks().get(0).getUrl()).toString();
        }

        StringBuilder links = new StringBuilder(StringUtils.EMPTY);
        for (ChangelogLink link : entry.getLinks()) {
            links.append(new Link(link.getName(), link.getUrl()).toString()).append(" ");
        }
        return links.toString().trim();
    }

    private String getAuthors() {
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

        return "@" + author.getNick();
    }


}
