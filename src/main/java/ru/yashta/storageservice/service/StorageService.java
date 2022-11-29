package ru.yashta.storageservice.service;

import ru.yashta.storageservice.model.ItemRequestDto;
import ru.yashta.storageservice.model.PathType;

import java.util.List;

public interface StorageService {

    void load(String path);
    void load(PathType type, String path);
    List<Integer> getItems(ItemRequestDto requestDto);
}
