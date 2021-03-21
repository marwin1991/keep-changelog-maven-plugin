package com.github.marwin1991.keep_changelog.yaml.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigurationAction {

    ADD("add", "Added"),
    UPDATE("update", "Updated"),
    DELETE("delete", "Deleted");

    private final String action;
    private final String displayText;
}
