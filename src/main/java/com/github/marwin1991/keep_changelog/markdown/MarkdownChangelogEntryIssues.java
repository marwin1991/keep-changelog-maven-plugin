package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class MarkdownChangelogEntryIssues extends MarkdownChangelogEntry {
    public MarkdownChangelogEntryIssues(ChangelogEntry entry) {
        super(entry);
    }

    protected String getIssues() {
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
}
