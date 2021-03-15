package com.github.marwin1991.keep_changelog.yaml.parse;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import de.beosign.snakeyamlanno.constructor.AnnotationAwareConstructor;
import lombok.SneakyThrows;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.github.marwin1991.keep_changelog.markdown.MarkdownChangelogVersion.releaseDateFormat;

public class ChangelogEntryParser {

    @SneakyThrows
    public static ChangelogEntry getChangelogEntriesFromFile(File file) {
        Yaml yaml = new Yaml(new AnnotationAwareConstructor(ChangelogEntry.class));
        return yaml.load(new FileInputStream(file));
    }

    @SneakyThrows
    public static OffsetDateTime getReleaseDateFromFile(File file) {
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        if(lines.size() == 0){
            return null;
        }

        return OffsetDateTime.of(
                LocalDate.parse(lines.get(0), DateTimeFormatter.ofPattern(releaseDateFormat)),
                LocalTime.NOON,
                ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }
}
