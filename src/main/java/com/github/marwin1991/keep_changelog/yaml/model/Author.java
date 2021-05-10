package com.github.marwin1991.keep_changelog.yaml.model;

import com.github.marwin1991.keep_changelog.logger.Logger;
import de.beosign.snakeyamlanno.property.YamlAnySetter;
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

    @YamlAnySetter
    public void anySetter(String key, Object value) {
        Logger.getLogger().warn("Unknown property: " + key + " with value " + value);
    }

}
