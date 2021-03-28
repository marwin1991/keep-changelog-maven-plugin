package com.github.marwin1991.keep_changelog;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;

@Mojo(name = "initProject", defaultPhase = LifecyclePhase.NONE)
public class InitProjectMojo extends AbstractMojo {

    @Parameter(defaultValue = "CHANGELOG.md", property = "changelogDirectory")
    private String changelogDirectory;

    @Parameter(defaultValue = "changelog/unreleased/.gitkeep", property = "gitkeepDirectory")
    private String gitkeepDirectory;

    @Parameter(defaultValue = "changelog", property = "changelogDirectory")
    private String changelogDirectoryName;

    @Override
    public void execute() {

        generateChangelog(changelogDirectory);
        generateChangelogDirUnreleasedDirGitKeep(gitkeepDirectory);
    }

    public void generateChangelog(String path) {

        try {
            File changelog = new File(path);
            if (changelog.createNewFile()) {
                getLog().info("Created: " + changelog.getName());
            } else {
                getLog().error(changelog.getName() + " already exists.");
            }
        } catch (IOException e) {
            getLog().error("An error occurred while creating empty changelog.");
            e.printStackTrace();
        }
    }

    public void generateChangelogDirUnreleasedDirGitKeep(String path) {

        try {
            File gitkeep = new File(path);
            if (gitkeep.createNewFile()) {
                getLog().info("Created: " + gitkeep.getName());
            } else {
                getLog().error(gitkeep.getName() + " already exists.");
            }
        } catch (IOException e) {
            getLog().error("An error occurred while creating empty .gitkeep.");
            e.printStackTrace();
        }
    }


}
