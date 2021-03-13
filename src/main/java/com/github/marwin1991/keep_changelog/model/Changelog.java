package com.github.marwin1991.keep_changelog.model;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Changelog {

    private String header;
    @Singular
    private List<ChangelogVersion> versions;

}
