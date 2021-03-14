package com.github.marwin1991.keep_changelog;

import com.github.marwin1991.keep_changelog.markdown.MarkdownChangelog;
import com.github.marwin1991.keep_changelog.model.Changelog;
import com.github.marwin1991.keep_changelog.model.ChangelogVersion;
import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.parse.ChangelogEntryParser;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Mojo(name = "generate", defaultPhase = LifecyclePhase.NONE)
public class GenerateChangelogMojo extends AbstractMojo {

    @Parameter(defaultValue = "", property = "header")
    private String header;

    @Parameter(defaultValue = "changelog", property = "changelogDirectory")
    private String changelogDirectoryName;

    @Parameter(defaultValue = "CHANGELOG.md", property = "finalChangelogName")
    private String finalChangelogName;

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        File changelogDirectory = findChangelogDirectory(project.getFile().getParent());

        generate(changelogDirectory, finalChangelogName);

        getLog().info("HELLO WORLD");
    }

    public void generate(File changelogDirectory, String finalChangelogName) {
        Changelog.ChangelogBuilder changelogBuilder = Changelog.builder();

        changelogBuilder.header(header);

        for (File version : changelogDirectory.listFiles()) {
            changelogBuilder.version(ChangelogVersion.builder()
                    .version(version.getName().replace("v", ""))
                    //used to skip "v" from directories names
                    // we can use "(?!\.)(\d+(\.\d+)+)([-.][A-Z]+)?(?![\d.])$" to get version and skipp all letters before version number
                    // but we have to make exception for "unreleased" string as it is not matching this regexp
                    .entries(getEntries(version))
                    .build());
        }

        MarkdownChangelog markdownChangelog = new MarkdownChangelog(changelogBuilder.build());
        System.out.println(markdownChangelog.toMarkdown());

        try (PrintWriter out = new PrintWriter(finalChangelogName)) {
            out.println(markdownChangelog.toMarkdown());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //TODO
        }
    }

    private Collection<? extends ChangelogEntry> getEntries(File version) {
        return Arrays.stream(version.listFiles())
                .map(ChangelogEntryParser::getChangelogEntriesFromFile)
                .collect(Collectors.toList());
    }


    private File findChangelogDirectory(String directoryPath) {
        File changelogDirectory = new File(directoryPath + "/" + changelogDirectoryName);
        if (!changelogDirectory.exists()) {
            getLog().error("There is no 'changelog' directory in this project !!!");
            throw new RuntimeException("No changelog directory");
        }

        if (!changelogDirectory.isDirectory()) {
            getLog().error("File 'changelog' is not a directory !!!");
            throw new RuntimeException("File changelog is not a directory");
        }

        return changelogDirectory;
    }
}