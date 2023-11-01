package university.project.controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class CustomExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleUpdatingNotFoundEntity() {
        String errorMessage = "Entity with provided id not found";
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
