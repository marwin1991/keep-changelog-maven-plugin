package com.github.marwin1991.keep_changelog;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.NONE)
public class GenerateChangelogMojo extends AbstractMojo {


    @Parameter(defaultValue = "changelog", property = "changelogDirectory")
    private String changelogDirectoryName;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        findChangelogDirectory(project.getFile().getParent());
        getLog().info("HELLO WORLD");
    }


    private File findChangelogDirectory(String directoryPath) {
        File changelogDirectory = new File(directoryPath + "/" + changelogDirectoryName);
        if (!changelogDirectory.exists()) {

        }

        if (!changelogDirectory.isDirectory()) {

        }
        return changelogDirectory;
    }
}