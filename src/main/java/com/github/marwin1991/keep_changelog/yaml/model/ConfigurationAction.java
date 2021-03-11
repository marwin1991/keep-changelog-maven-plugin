package com.github.marwin1991.keep_changelog.yaml.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConfigurationAction {

    ADD("add"),
    UPDATE("update"),
    DELETE("delete");

    private final String action;
}
