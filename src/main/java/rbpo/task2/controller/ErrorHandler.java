package rbpo.task2.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice //ловит ошибки из всех контроллеров
public class ErrorHandler {

    // база не приняла данные (повторяющийся email или номер, пустое поле, плохое значение) - отвечаем 409, а не 500
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> handleDataIntegrity() {
        return Map.of("message", "Данные не прошли проверку в базе: такое значение уже есть или поле заполнено неверно");
    }
}
