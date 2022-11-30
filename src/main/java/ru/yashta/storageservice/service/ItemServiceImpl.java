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
        String color = requestDto.getColor();
        Integer boxId = requestDto.getBox();
        if (hasColor(color) && hasBoxContainer(boxId)) {
            return itemRepository.findItems(color, boxId);
        }
        if (hasBoxContainer(boxId)) {
            return itemRepository.findItemsByBoxIdWithoutColor(boxId);
        }
        if (hasColor(color)) {
            return itemRepository.findItemsByColorWithoutBoxId(color);
        }
        return itemRepository.findItemsWithoutBoxIdAndColor();
    }

    private boolean hasColor(String color) {
        return color != null && !color.isBlank();
    }

    private boolean hasBoxContainer(Integer box) {
        return box != null;
    }
}
