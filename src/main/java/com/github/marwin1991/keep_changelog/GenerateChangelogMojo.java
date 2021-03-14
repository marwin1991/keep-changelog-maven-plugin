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
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
        List<String> archive = new LinkedList<>();

        for (File file : changelogDirectory.listFiles()) {
            if (file.isDirectory()) {
                changelogBuilder.version(ChangelogVersion.builder()
                        .version(file.getName().replace("v", ""))
                        //used to skip "v" from directories names
                        // we can use "(?!\.)(\d+(\.\d+)+)([-.][A-Z]+)?(?![\d.])$" to get version and skipp all letters before version number
                        // but we have to make exception for "unreleased" string as it is not matching this regexp
                        .entries(getEntries(file))
                        .build());
            } else if (file.getName().startsWith("archive")) { // TODO extract to parameter
                try {
                    archive.addAll(Files.readAllLines(file.toPath(), StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                    //TODO
                }
            }
        }

        MarkdownChangelog markdownChangelog = new MarkdownChangelog(changelogBuilder.build());
        try (PrintWriter out = new PrintWriter(finalChangelogName)) {
            out.println(markdownChangelog.toMarkdown());
            for (String line : archive) {
                out.println(line);
            }
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