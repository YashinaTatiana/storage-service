package ru.yashta.storageservice.service;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yashta.storageservice.entity.Box;
import ru.yashta.storageservice.entity.Item;
import ru.yashta.storageservice.exception.InvalidPathFormatException;
import ru.yashta.storageservice.exception.StorageParseException;
import ru.yashta.storageservice.model.BoxDto;
import ru.yashta.storageservice.model.ItemRequestDto;
import ru.yashta.storageservice.model.PathType;
import ru.yashta.storageservice.model.Storage;
import ru.yashta.storageservice.parser.StorageParserFactory;
import ru.yashta.storageservice.repository.BoxRepository;
import ru.yashta.storageservice.repository.ItemRepository;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {

    private final StorageParserFactory parserFactory;
    private final ItemRepository itemRepository;
    private final BoxRepository boxRepository;

    @Override
    public void load(String path) {
        int index = path.indexOf(":");
        if (index == -1) {
            throw new InvalidPathFormatException("Path should have format - type:path");
        }
        load(PathType.fromString(path.substring(0, index)), path.substring(index+1));
    }

    public void load(PathType type, String path) {
        try {
            Storage storage = parserFactory.getStorageParserByPath(type).parseXmlFile(path);
            load(storage);
        } catch (IOException | JAXBException e) {
            log.error("Error occurred during parsing xml", e);
            throw new StorageParseException(e.getMessage());
        }
    }

    @Transactional
    public void load(Storage storage) {
        storage.getItems().forEach(item -> itemRepository.save(Item.of(item)));
        storage.getBoxes().forEach(box -> load(box, null));
    }

    private void load(BoxDto boxDto, Box container) {
        Box box = Box.builder()
                .id(boxDto.getId())
                .containedIn(container)
                .build();

        boxDto.getItems().forEach(item -> box.addItem(Item.of(item)));
        boxRepository.save(box);

        boxDto.getBoxes().forEach(innerBox -> load(innerBox, box));
    }

    @Override
    public List<Integer> getItems(ItemRequestDto requestDto) {
        return itemRepository.findItems(requestDto.getColor(), requestDto.getBox());
    }
}
