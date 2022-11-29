package ru.yashta.storageservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yashta.storageservice.model.ErrorDto;
import ru.yashta.storageservice.model.ErrorListDto;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorListDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorListDto errorListDto = new ErrorListDto();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errorListDto.addError(ErrorDto.builder()
                    .field(((FieldError) error).getField())
                    .message(error.getDefaultMessage())
                    .build());
        });
        return errorListDto;
    }
}
