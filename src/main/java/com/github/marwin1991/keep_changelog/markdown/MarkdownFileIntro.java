package com.github.marwin1991.keep_changelog.markdown;

public class MarkdownFileIntro implements Markdown {

    private static final String FORMATTER_OFF = "<!-- @formatter:off -->";
    private static final String NOINSPECTION = "<!-- noinspection -->";
    private static final String COMMENT = "<!-- Prevents auto format, for JetBrains IDE File > Editor > Code Style > Enable formatter markers in comments  -->";


    @Override
    public String toMarkdown() {
        return getFileIntro();
    }

    @Override
    public String toString() {
        return toMarkdown();
    }

    public String getFileIntro() {
        return FORMATTER_OFF + "\n" +
                NOINSPECTION + "\n" +
                COMMENT + "\n\n";
    }
}
