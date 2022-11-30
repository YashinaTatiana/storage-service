package ru.yashta.storageservice.parser;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yashta.storageservice.model.LinkType;
import ru.yashta.storageservice.model.Storage;

import java.io.IOException;
import java.net.URL;

@Slf4j
@RequiredArgsConstructor
@Component
public class URLStorageParser implements StorageParser {

    private final Unmarshaller storageUnmarshaller;

    @Override
    public Storage parseXmlFile(String path) throws JAXBException, IOException {
       return (Storage) storageUnmarshaller.unmarshal(new URL(path));
    }

    @Override
    public LinkType getLinkType() {
        return LinkType.URL;
    }
}
