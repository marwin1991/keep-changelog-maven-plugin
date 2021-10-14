package com.github.marwin1991.keep_changelog.markdown;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

class MarkdownListInTable implements Markdown {

    private static final String beginTag = "<ul>";
    private static final String endTag = "</ul>";

    private static final String beginItemTag = "<li>";
    private static final String endItemTag = "</li>";

    private final List<String> items = new LinkedList<>();

    public void add(String item) {
        if (StringUtils.isNotBlank(item)) {
            items.add(item);
        }
    }

    @Override
    public String toMarkdown() {
        if (items.size() == 0) {
            return StringUtils.EMPTY;
        }

        StringBuilder str = new StringBuilder(beginTag);

        for (String item : items) {
            str.append(beginItemTag).append(item).append(endItemTag);
        }

        str.append(endTag);

        return str.toString();
    }

    @Override
    public String toString() {
        return toMarkdown();
    }
}
