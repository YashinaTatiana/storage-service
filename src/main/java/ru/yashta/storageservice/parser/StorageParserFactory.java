package ru.yashta.storageservice.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yashta.storageservice.exception.UnsupportedPathTypeException;
import ru.yashta.storageservice.model.PathType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/***
 * Factory of storage parser strategies,
 * where parserStrategies map contains:
 * { CLASSPATH -> ClassPathStorageParser,
 *   FILE -> FileStorageParser,
 *   URL -> URLStorageParser }
 */
@Component
public class StorageParserFactory {

    private final Map<PathType, StorageParser> parserStrategies;

    @Autowired
    public StorageParserFactory(Set<StorageParser> storageParsers) {
        parserStrategies = new HashMap<>();
        storageParsers.forEach(parser -> parserStrategies.put(parser.getPathType(), parser));
    }

    public StorageParser getStorageParserByPath(PathType pathType) {
        StorageParser parser = parserStrategies.get(pathType);
        if (null == parser) {
            throw new UnsupportedPathTypeException();
        }
        return parser;
    }
}