package com.github.marwin1991.keep_changelog.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import static com.github.marwin1991.keep_changelog.mojo.GenerateChangelogMojo.RELEASE_DATE;
import static com.github.marwin1991.keep_changelog.mojo.InitProjectMojo.GIT_KEEP;

@Mojo(name = "release", defaultPhase = LifecyclePhase.NONE)
public class CreateReleaseMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    @Parameter(defaultValue = "changelog", property = "yamlFilesDirectory")
    private String yamlFilesDirectory;

    @Parameter(defaultValue = "unreleased", property = "unreleasedVersionDirectory")
    private String unreleasedVersionDirectory;

    @Parameter(defaultValue = "CHANGELOG.md", property = "finalChangelogName")
    private String finalChangelogName;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Begin preparation from new changelog release");

        String unreleasedDir = yamlFilesDirectory + "/" + unreleasedVersionDirectory;
        String newDirName = yamlFilesDirectory + "/" + "v" + project.getVersion().replace("-SNAPSHOT", "");

        addReleaseDate(unreleasedDir);
        removeGitKeep(unreleasedDir);
        renameUnreleasedDir(unreleasedDir, newDirName);

        GenerateChangelogMojo generateChangelogMojo = new GenerateChangelogMojo();
        generateChangelogMojo.setLog(getLog());
        generateChangelogMojo.executeGenerate(finalChangelogName, project, yamlFilesDirectory);

        //TODO newest release save to file file release_notes.txt to sue it during release creation
        new InitProjectMojo().generateChangelogDirUnreleasedDirGitKeep(yamlFilesDirectory + "/" + unreleasedVersionDirectory + "/");
        getLog().info("New changelog release successful");
    }

    private void renameUnreleasedDir(String unreleasedDirName, String newDirName) {
        File unreleasedDir = new File(unreleasedDirName);
        File newDir = new File(newDirName);
        if (unreleasedDir.renameTo(newDir)) {
            getLog().info("Renamed " + unreleasedDirName + " to " + newDirName + " successful");
        } else {
            //TODO throw exception
        }

    }

    private void addReleaseDate(String unreleasedDir) {
        try {
            File releaseDateFile = new File(unreleasedDir + "/" + RELEASE_DATE);
            if (releaseDateFile.createNewFile()) {
                FileWriter fileWriter = new FileWriter(releaseDateFile);
                fileWriter.write(LocalDate.now().toString());
                fileWriter.close();
                getLog().info("Created: " + releaseDateFile.getName());
            } else {
                getLog().warn(releaseDateFile.getName() + " already exists.");
            }
        } catch (IOException e) {
            getLog().error("An error occurred while creating " + RELEASE_DATE, e);
        }
    }

    private void removeGitKeep(String unreleasedDir) {
        File gitKeep = new File(unreleasedDir + "/" + GIT_KEEP);
        if (gitKeep.delete()) {
            getLog().info("Deleted: " + gitKeep.getName());
        } else {
            getLog().warn(gitKeep.getName() + " cannot be deleted.");
        }
    }

}
