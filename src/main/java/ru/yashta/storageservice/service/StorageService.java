package ru.yashta.storageservice.service;

import ru.yashta.storageservice.model.PathType;


public interface StorageService {

    void load(String link);
    void load(PathType type, String path);
}
