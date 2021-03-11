package com.github.marwin1991.keep_changelog.yaml.model;

import com.github.marwin1991.keep_changelog.yaml.converter.ChangeLogEntryTypeConverter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangelogEntry {

    private String title;
    private List<Author> author;
    private String mergeRequest;
    private String issue;
    private List<Link> links;
    private ChangeLogEntryType type;
    private String versionRecommendations;
    private List<Configuration> configuration;

    @YamlProperty(key = "merge_request")
    public void setMergeRequest(String mergeRequest) {
        this.mergeRequest = mergeRequest;
    }

    @YamlProperty(key = "type", converter = ChangeLogEntryTypeConverter.class)
    public void setType(ChangeLogEntryType type) {
        this.type = type;
    }

    @YamlProperty(key = "version_recommendations")
    public void setVersionRecommendations(String versionRecommendations) {
        this.versionRecommendations = versionRecommendations;
    }
}
