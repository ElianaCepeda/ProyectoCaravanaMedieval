package co.edu.javeriana.juego_caravana_medieval.exception_handler;

import java.util.NoSuchElementException;

import co.edu.javeriana.juego_caravana_medieval.CaravanaMedievalApplication;
import co.edu.javeriana.juego_caravana_medieval.DTO.ErrorDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppControllerAdvice {

    AppControllerAdvice(CaravanaMedievalApplication caravanaMedievalApplication) {
    }

    @ExceptionHandler (NoSuchElementException.class)
    public ResponseEntity<ErrorDTO> handelNotFoundException(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Elemento no encontrado"));
    }



}