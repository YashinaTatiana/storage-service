package ru.yashta.storageservice.model;


import ru.yashta.storageservice.exception.InvalidPathFormatException;

import java.util.Arrays;

public enum PathType {
    FILE, URL, CLASSPATH;

    public static PathType fromString(String type) {
        try {
            return PathType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPathFormatException(
                    "Not supported path type: [%s]. Supported values: %s"
                            .formatted(type, Arrays.asList(PathType.values())));
        }
    }
}
