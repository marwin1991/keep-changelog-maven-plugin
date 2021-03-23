package com.github.marwin1991.keep_changelog;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;

@Mojo(name = "initGenerate", defaultPhase = LifecyclePhase.NONE)
public class InitProjectMojo extends AbstractMojo {

    @Parameter(defaultValue = "CHANGELOG.md", property = "finalChangelogName")
    private String finalChangelogName;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() {

        generateChangelog(finalChangelogName);

        getLog().info("HELLO WORLD");
    }

    public void generateChangelog(String path) {

        try {
            File changelog = new File(path);
            if (changelog.createNewFile()) {
                System.out.println("Created: " + changelog.getName());
            } else {
                System.out.println(changelog.getName() + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating empty changelog.");
            e.printStackTrace();
        }
    }

    public void generateGitkeep(String path) {

        try {
            File gitkeep = new File(path);
            if (gitkeep.createNewFile()) {
                System.out.println("Created: " + gitkeep.getName());
            } else {
                System.out.println(gitkeep.getName() + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating empty .gitkeep.");
            e.printStackTrace();
        }
    }


}
