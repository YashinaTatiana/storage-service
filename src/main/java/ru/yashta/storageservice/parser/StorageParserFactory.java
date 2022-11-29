package ru.yashta.storageservice.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yashta.storageservice.exception.UnsupportedPathTypeException;
import ru.yashta.storageservice.model.PathType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class StorageParserFactory {

    private final Map<PathType, StorageParser> storageParserStrategy;

    @Autowired
    public StorageParserFactory(Set<StorageParser> storageParsers) {
        storageParserStrategy = new HashMap<>();
        storageParsers.forEach(parser -> storageParserStrategy.put(parser.getPathType(), parser));
    }

    public StorageParser getStorageParserByPath(PathType pathType) {
        StorageParser parser = storageParserStrategy.get(pathType);
        if (null == parser) {
            throw new UnsupportedPathTypeException();
        }
        return parser;
    }
}