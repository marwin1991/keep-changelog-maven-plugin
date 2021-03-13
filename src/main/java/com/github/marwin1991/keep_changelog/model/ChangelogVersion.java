package com.github.marwin1991.keep_changelog.model;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangelogVersion {

    private String version;
    private OffsetDateTime releaseDateTime;
    @Singular
    private List<ChangelogEntry> entries;

}
