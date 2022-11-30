package ru.yashta.storageservice.model;


import ru.yashta.storageservice.exception.InvalidPathFormatException;

import java.util.Arrays;

public enum LinkType {
    FILE, URL, CLASSPATH;

    public static LinkType fromString(String type) {
        try {
            return LinkType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPathFormatException(
                    "Not supported path type: [%s]. Supported values: %s"
                            .formatted(type, Arrays.asList(LinkType.values())));
        }
    }
}
