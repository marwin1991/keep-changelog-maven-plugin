package com.github.marwin1991.keep_changelog.yaml.model;

import com.github.marwin1991.keep_changelog.yaml.converter.ConfigurationActionConverter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    private ConfigurationAction action;
    private String name;
    private String defaultValue;
    private String description;
    private String type;

    @YamlProperty(key = "action", converter = ConfigurationActionConverter.class)
    public void setAction(ConfigurationAction action) {
        this.action = action;
    }

    @YamlProperty(key = "default_value")
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
