package ru.yashta.storageservice.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ItemRequestDto {

    @NotNull
    private String color;
    @NotNull
    private Integer box;
}
