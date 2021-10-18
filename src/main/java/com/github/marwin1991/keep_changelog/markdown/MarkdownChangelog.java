package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.model.Changelog;
import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import net.steppschuh.markdowngenerator.text.Text;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;

public class MarkdownChangelog implements Markdown {

    private static final String PREVENT_FORMAT_AND_INSPECTION_DISCLAIMER =
            "<!-- @formatter:off --><!-- noinspection -->\n<!-- Prevents auto format, for JetBrains IDE File > Editor > Code Style > Enable formatter markers in comments  -->\n\n";

    private final Changelog changelog;
    private LinkedHashMap<String, String> versionsSummaries;

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
        this.versionsSummaries = new LinkedHashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(PREVENT_FORMAT_AND_INSPECTION_DISCLAIMER);
        stringBuilder.append(getHeader());

        if(changelog.getVersions() != null){
            for (ChangelogVersion version : changelog.getVersions()) {
                String versionContent = new MarkdownChangelogVersion(version).toMarkdown();
                versionsSummaries.put(version.getVersion(), versionContent);
                stringBuilder.append(versionContent).append("\n");
            }
        }

        return stringBuilder.toString();
    }

    public String getVersionSummary(String version) {
        if (versionsSummaries == null) {
            throw new IllegalStateException("MarkdownChangelog have to generate changelog first!");
        }

        return versionsSummaries.get(version);
    }

    private String getHeader() {
        if (StringUtils.isBlank(changelog.getHeader())) {
            return StringUtils.EMPTY;
        }
        return new Text(changelog.getHeader()) + "\n";
    }

}
