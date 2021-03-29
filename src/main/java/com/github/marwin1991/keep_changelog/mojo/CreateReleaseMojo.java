package com.github.marwin1991.keep_changelog.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "release", defaultPhase = LifecyclePhase.NONE)
public class CreateReleaseMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "changelog", property = "yamlFilesDirectory")
    private String yamlFilesDirectory;

    @Parameter(defaultValue = "unreleased", property = "unreleasedVersionDirectory")
    private String unreleasedVersionDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

    }
}
