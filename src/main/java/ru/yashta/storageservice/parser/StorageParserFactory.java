package ru.yashta.storageservice.parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yashta.storageservice.exception.UnsupportedLinkTypeException;
import ru.yashta.storageservice.model.LinkType;

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

    private final Map<LinkType, StorageParser> parserStrategies;

    @Autowired
    public StorageParserFactory(Set<StorageParser> storageParsers) {
        parserStrategies = new HashMap<>();
        storageParsers.forEach(parser -> parserStrategies.put(parser.getLinkType(), parser));
    }

    public StorageParser getStorageParserByPath(LinkType linkType) {
        StorageParser parser = parserStrategies.get(linkType);
        if (null == parser) {
            throw new UnsupportedLinkTypeException();
        }
        return parser;
    }
}