package com.github.marwin1991.keep_changelog.test;

import com.github.marwin1991.keep_changelog.GenerateChangelogMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import java.io.File;

public class TestGenerateChangelogMojo {

    public static void main(String[] args) throws MojoFailureException, MojoExecutionException {
        new GenerateChangelogMojo().generate(new File("./changelog"), "src/CHANGELOG.md");
    }
}
