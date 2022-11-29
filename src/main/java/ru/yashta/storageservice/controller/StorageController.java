package ru.yashta.storageservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yashta.storageservice.model.ItemRequestDto;
import ru.yashta.storageservice.service.StorageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class StorageController {

    private final StorageService service;

    @PostMapping
    public ResponseEntity<List<Integer>> getItemsInBoxByColor(@Valid @RequestBody ItemRequestDto requestDto) {
       return ResponseEntity.ok(service.getItems(requestDto));
    }
}
