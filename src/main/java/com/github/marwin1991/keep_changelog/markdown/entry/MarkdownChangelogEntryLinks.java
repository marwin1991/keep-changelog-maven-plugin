package com.github.marwin1991.keep_changelog.markdown.entry;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogLink;
import net.steppschuh.markdowngenerator.link.Link;
import org.apache.commons.lang3.StringUtils;

public class MarkdownChangelogEntryLinks extends MarkdownChangelogEntryAbstract {

    public MarkdownChangelogEntryLinks(ChangelogEntry entry) {
        super(entry);
    }

    public String getLinks() {
        if (entry.getLinks() == null || entry.getLinks().size() == 0) {
            return StringUtils.EMPTY;
        }

        if (entry.getLinks().size() == 1) {
            return new Link(entry.getLinks().get(0).getName(), entry.getLinks().get(0).getUrl()).toString();
        }

        StringBuilder links = new StringBuilder(StringUtils.EMPTY);
        for (ChangelogLink link : entry.getLinks()) {
            links.append(new Link(link.getName(), link.getUrl())).append(" ");
        }
        return links.toString().trim();
    }
}

