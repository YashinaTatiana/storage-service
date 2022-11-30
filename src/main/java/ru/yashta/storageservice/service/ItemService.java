package ru.yashta.storageservice.service;

import ru.yashta.storageservice.model.ItemRequestDto;

import java.util.List;

public interface ItemService {

   List<Integer> getItems(ItemRequestDto requestDto);
}
