package ru.yashta.storageservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yashta.storageservice.model.ItemRequestDto;
import ru.yashta.storageservice.repository.ItemRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public List<Integer> getItems(ItemRequestDto requestDto) {
        return itemRepository.findItems(requestDto.getColor(), requestDto.getBox());
    }
}
