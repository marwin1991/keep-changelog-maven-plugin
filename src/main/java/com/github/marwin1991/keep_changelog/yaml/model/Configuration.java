package com.github.marwin1991.keep_changelog.yaml.model;

import com.github.marwin1991.keep_changelog.logger.Logger;
import com.github.marwin1991.keep_changelog.yaml.converter.ConfigurationActionConverter;
import de.beosign.snakeyamlanno.property.YamlAnySetter;
import de.beosign.snakeyamlanno.property.YamlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Configuration implements Comparable<Configuration> {

    private String type;
    private ConfigurationAction action;
    private String key;
    private String defaultValue;
    private String description;
    private String moreInfo;

    @YamlProperty(key = "action", converter = ConfigurationActionConverter.class)
    public void setAction(ConfigurationAction action) {
        this.action = action;
    }

    @YamlProperty(key = "default_value")
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @YamlProperty(key = "more_info")
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

    @Override
    public int compareTo(Configuration o) {
        return Comparator.comparing(Configuration::getType)
                .thenComparing(Configuration::getAction)
                .thenComparing(Configuration::getKey)
                .compare(this, o);
    }
}
