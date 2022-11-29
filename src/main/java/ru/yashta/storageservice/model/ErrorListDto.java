package ru.yashta.storageservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorListDto {
    private List<ErrorDto> errors;

    public void addError(ErrorDto errorDto) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(errorDto);
    }
}
