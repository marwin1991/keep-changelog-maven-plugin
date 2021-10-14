package com.github.marwin1991.keep_changelog.markdown.entry;

import com.github.marwin1991.keep_changelog.markdown.Markdown;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import net.steppschuh.markdowngenerator.list.UnorderedListItem;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

class MarkdownChangelogEntry extends MarkdownChangelogEntryAbstract implements Markdown {

    private static final String entryFormat = "${title} ${merge_request_format} ${issues} ${links} ${authors}";
    private final MarkdownChangelogEntryMergeRequest markdownChangelogEntryMergeRequest;
    private final MarkdownChangelogEntryIssues markdownChangelogEntryIssues;
    private final MarkdownChangelogEntryLinks markdownChangelogEntryLinks;
    private final MarkdownChangelogEntryAuthor markdownChangelogEntryAuthor;

    public MarkdownChangelogEntry(ChangelogEntry entry) {
        super(entry);
        markdownChangelogEntryMergeRequest = new MarkdownChangelogEntryMergeRequest(entry);
        markdownChangelogEntryIssues = new MarkdownChangelogEntryIssues(entry);
        markdownChangelogEntryLinks = new MarkdownChangelogEntryLinks(entry);
        markdownChangelogEntryAuthor = new MarkdownChangelogEntryAuthor(entry);
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
        valuesMap.put("merge_request_format", markdownChangelogEntryMergeRequest.getMergeRequest());
        valuesMap.put("issues", markdownChangelogEntryIssues.getIssues());
        valuesMap.put("links", markdownChangelogEntryLinks.getLinks());
        valuesMap.put("authors", markdownChangelogEntryAuthor.getAuthors());

        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(entryFormat).replaceAll("\\s{2,}", " ");
    }
}
