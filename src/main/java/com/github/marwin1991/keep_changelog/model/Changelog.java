package com.github.marwin1991.keep_changelog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Changelog {

    private String header;
    private List<ChangelogVersion> versions;

}
