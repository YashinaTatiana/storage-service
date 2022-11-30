package ru.yashta.storageservice.service;

import ru.yashta.storageservice.model.LinkType;


public interface StorageService {

    void load(String link);
    void load(LinkType type, String path);
}
