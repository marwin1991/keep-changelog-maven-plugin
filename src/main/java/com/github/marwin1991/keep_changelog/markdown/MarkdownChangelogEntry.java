package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import net.steppschuh.markdowngenerator.list.UnorderedListItem;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class MarkdownChangelogEntry implements Markdown {

    protected static final String entryFormat = "${title} ${merge_request_format} ${issues} ${links} ${authors}";
    protected static final String mergeRequestFormat = "!${merge_request}";
    protected static final String issueFormat = "#${issue}";
    protected static final String authorFormat = "(${author})";

    protected final ChangelogEntry entry;

    public MarkdownChangelogEntry(ChangelogEntry entry) {
        this.entry = entry;
    }

    @Override
    public String toMarkdown() {
        return new UnorderedListItem(getEntry()).toString();
    }

    @Override
    public String toString() {
        return toMarkdown();
    }

    private String getEntry() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("title", entry.getTitle());
        valuesMap.put("merge_request_format", new MarkdownChangelogEntryMergeRequest(entry).getMergeRequest());
        valuesMap.put("issues", new MarkdownChangelogEntryIssues(entry).getIssues());
        valuesMap.put("links", new MarkdownChangelogEntryLinks(entry).getLinks());
        valuesMap.put("authors", new MarkdownChangelogEntryAuthor(entry).getAuthors());

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(entryFormat).replaceAll("\\s{2,}", " ");
    }
}
