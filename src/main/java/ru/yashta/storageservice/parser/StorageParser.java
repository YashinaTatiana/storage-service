package ru.yashta.storageservice.parser;

import jakarta.xml.bind.JAXBException;
import ru.yashta.storageservice.model.PathType;
import ru.yashta.storageservice.model.Storage;

import java.io.IOException;


public interface StorageParser {

    Storage parseXmlFile(String path) throws JAXBException, IOException;

    PathType getPathType();
}
