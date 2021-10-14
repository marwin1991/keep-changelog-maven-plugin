package com.github.marwin1991.keep_changelog.release_date.parser;

import lombok.SneakyThrows;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.github.marwin1991.keep_changelog.release_date.format.DateFormat.RELEASE_DATE_FORMAT;

public class ReleaseDateFileParser {

    @SneakyThrows
    public static OffsetDateTime getReleaseDateFromFile(File file) {
        List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

        if (lines.size() == 0) {
            return null;
        }

        return OffsetDateTime.of(
                LocalDate.parse(lines.get(0), DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT)),
                LocalTime.NOON,
                ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now()));
    }
}
