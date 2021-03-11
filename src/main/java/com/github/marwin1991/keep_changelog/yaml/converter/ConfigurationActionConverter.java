package com.github.marwin1991.keep_changelog.yaml.converter;

import com.github.marwin1991.keep_changelog.yaml.model.ConfigurationAction;
import de.beosign.snakeyamlanno.convert.Converter;

public class ConfigurationActionConverter implements Converter<ConfigurationAction> {

    @Override
    public String convertToYaml(ConfigurationAction configurationAction) {
        return configurationAction.getAction();
    }

    @Override
    public ConfigurationAction convertToModel(Object o) {
        for (ConfigurationAction configurationAction : ConfigurationAction.values()) {
            if (configurationAction.getAction().equals(o)) {
                return configurationAction;
            }
        }

        throw new IllegalArgumentException("Illegal value of action: " + toString());
    }

}
