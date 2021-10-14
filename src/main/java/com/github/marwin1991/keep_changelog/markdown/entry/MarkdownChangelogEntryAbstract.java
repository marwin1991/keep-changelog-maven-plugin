package com.github.marwin1991.keep_changelog.markdown.entry;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;

abstract class MarkdownChangelogEntryAbstract {
    final ChangelogEntry entry;

    public MarkdownChangelogEntryAbstract(ChangelogEntry entry) {
        this.entry = entry;
    }
}
