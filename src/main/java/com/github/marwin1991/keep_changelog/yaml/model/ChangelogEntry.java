package com.github.marwin1991.keep_changelog.yaml.model;

import com.github.marwin1991.keep_changelog.logger.Logger;
import com.github.marwin1991.keep_changelog.yaml.converter.ChangeLogEntryTypeConverter;
import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.logging.SystemStreamLog;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangelogEntry {

    private String title;
    private List<Author> authors;
    private String mergeRequest;
    private List<String> issues;
    private List<ChangelogLink> links;
    private ChangeLogEntryType type;
    private List<String> importantNotes;
    private List<Configuration> configuration;

    @YamlProperty(key = "merge_request")
    public void setMergeRequest(String mergeRequest) {
        this.mergeRequest = mergeRequest;
    }

    @YamlProperty(key = "type", converter = ChangeLogEntryTypeConverter.class)
    public void setType(ChangeLogEntryType type) {
        this.type = type;
    }

    @YamlProperty(key = "important_notes")
    public void setImportantNotes(List<String> importantNotes) {
        this.importantNotes = importantNotes;
    }

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }
}
