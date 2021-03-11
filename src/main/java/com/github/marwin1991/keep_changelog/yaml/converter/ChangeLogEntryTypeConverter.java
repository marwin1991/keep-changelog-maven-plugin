package com.github.marwin1991.keep_changelog.yaml.converter;

import com.github.marwin1991.keep_changelog.yaml.model.ChangeLogEntryType;
import de.beosign.snakeyamlanno.convert.Converter;

public class ChangeLogEntryTypeConverter implements Converter<ChangeLogEntryType> {

    @Override
    public String convertToYaml(ChangeLogEntryType changeLogEntryType) {
        return changeLogEntryType.getType();
    }

    @Override
    public ChangeLogEntryType convertToModel(Object o) {
        for (ChangeLogEntryType changeLogEntryType : ChangeLogEntryType.values()) {
            if (changeLogEntryType.getType().equals(o)) {
                return changeLogEntryType;
            }
        }

        throw new IllegalArgumentException("Illegal value of type: " + toString());
    }

}

