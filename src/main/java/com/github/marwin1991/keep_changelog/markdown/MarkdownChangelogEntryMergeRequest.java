package com.github.marwin1991.keep_changelog.markdown;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class MarkdownChangelogEntryMergeRequest extends MarkdownChangelogEntry {
    public MarkdownChangelogEntryMergeRequest(ChangelogEntry entry) {
        super(entry);
    }

    protected String getMergeRequest() {
        if (StringUtils.isBlank(entry.getMergeRequest())) {
            return StringUtils.EMPTY;
        }

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("merge_request", entry.getMergeRequest());
        StringSubstitutor sub = new StringSubstitutor(valuesMap);
        return sub.replace(mergeRequestFormat);
    }
}
