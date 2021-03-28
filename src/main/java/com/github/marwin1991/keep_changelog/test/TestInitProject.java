package com.github.marwin1991.keep_changelog.test;

import com.github.marwin1991.keep_changelog.InitProjectMojo;

public class TestInitProject {

    public static void main(String[] args) {
        new InitProjectMojo().generateChangelog("CHANGELOG.md");
        new InitProjectMojo().generateChangelogDirUnreleasedDirGitKeep("changelog/unreleased/.gitkeep");
    }
}
