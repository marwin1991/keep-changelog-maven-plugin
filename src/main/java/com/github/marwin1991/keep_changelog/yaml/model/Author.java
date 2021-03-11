package com.github.marwin1991.keep_changelog.yaml.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private String name;
    private String nick;
    private String url;

}
