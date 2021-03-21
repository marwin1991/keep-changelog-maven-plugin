package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.model.Changelog;
import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import net.steppschuh.markdowngenerator.text.Text;
import org.apache.commons.lang3.StringUtils;

public class MarkdownChangelog implements Markdown {

    private final Changelog changelog;

    public MarkdownChangelog(Changelog changelog) {
        this.changelog = changelog;
    }

    @Override
    public String toMarkdown() {
        return getChangelog();
    }

    @Override
    public String toString() {
        return toMarkdown();
    }


    public String getChangelog() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getHeader());

        for (ChangelogVersion version : changelog.getVersions()) {
            stringBuilder.append(new MarkdownChangelogVersion(version)).append("\n");
        }

        return stringBuilder.toString();
    }


    private String getHeader() {
        if (StringUtils.isBlank(changelog.getHeader())) {
            return StringUtils.EMPTY;
        }
        return new Text(changelog.getHeader()) + "\n";
    }

}
