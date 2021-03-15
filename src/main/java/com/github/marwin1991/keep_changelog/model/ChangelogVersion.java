package com.github.marwin1991.keep_changelog.model;

import com.github.marwin1991.keep_changelog.yaml.model.ChangelogEntry;
import com.github.marwin1991.keep_changelog.yaml.model.Configuration;
import lombok.*;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangelogVersion implements Comparable<ChangelogVersion> {

    private String version;
    private OffsetDateTime releaseDateTime;
    @Singular
    private List<ChangelogEntry> entries;

    private ComparableVersion getComparableVersion() {
        return new ComparableVersion(version);
    }

    @Override
    public int compareTo(ChangelogVersion o) {
        if("unreleased".equals(version)){ //TODO extract this string
            return 1;
        }

        if("unreleased".equals(o.getVersion())){ //TODO extract this string
            return -1;
        }

        if(version.equals(o.getVersion())){
            return 0;
        }

        return Comparator.comparing(ChangelogVersion::getComparableVersion)
                .compare(this, o);
    }
}
