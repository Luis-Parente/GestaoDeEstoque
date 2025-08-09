package com.cosmeticosespacol.GestaoDeEstoque.excecao;

import com.cosmeticosespacol.GestaoDeEstoque.excecao.dto.ErroCustomizado;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExcecaoHandler {

    @ExceptionHandler(NaoEncontradoExcecao.class)
    public ResponseEntity<ErroCustomizado> entidadeNaoEncontrada(NaoEncontradoExcecao e,
                                                                 HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroCustomizado err = new ErroCustomizado(Instant.now(), status.value(), e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroCustomizado> illegalArgumentException(IllegalArgumentException e,
                                                                    HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErroCustomizado err = new ErroCustomizado(Instant.now(), status.value(), e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
