package com.github.marwin1991.keep_changelog.yaml.parse;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import de.beosign.snakeyamlanno.constructor.AnnotationAwareConstructor;
import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;

public class ChangelogEntryParser {

    @SneakyThrows
    public static ChangelogEntry getChangelogEntriesFromFile(File file) {
        Yaml yaml = new Yaml(new AnnotationAwareConstructor(ChangelogEntry.class));
        return yaml.load(new FileInputStream(file));
    }

    public static void main(String[] args) {
        getChangelogEntriesFromFile(new File("./changelog/unreleased/test-task.yml"));
    }
}
